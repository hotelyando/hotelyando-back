package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.RoomTypeDao;
import co.com.hotelyando.database.model.RoomType;
import co.com.hotelyando.database.model.RoomType.PriceDetails;

@Service
public class RoomTypeService {

	@Autowired
	private MessageSource messageSource;

	private RoomTypeDao roomTypeDao;

	private RegularExpression regularExpression;
	
	public RoomTypeService(RoomTypeDao roomTypeDao) {
		this.roomTypeDao = roomTypeDao;
		
		regularExpression = new RegularExpression();
	}

	
	/*
	 * Método que se encarga de registrar un tipo de habitación
	 * @return String
	 */
	public String save(RoomType roomType) throws MongoException, Exception {

		String messageReturn = "";
		
		if (StringUtils.isBlank(roomType.getUuid())) {
			messageReturn = messageSource.getMessage("roomtype.id", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(roomType.getHotelId())) {
			messageReturn = messageSource.getMessage("roomtype.hotelId", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(roomType.getDescription())) {
			messageReturn = messageSource.getMessage("roomtype.description", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(roomType.getPriceDay().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_day", null, LocaleContextHolder.getLocale());
		} else if (regularExpression.validateNumeric(roomType.getPriceDay().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_day_numeric", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(roomType.getPriceHour().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_hour", null, LocaleContextHolder.getLocale());
		} else if (regularExpression.validateNumeric(roomType.getPriceHour().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_hour_numeric", null, LocaleContextHolder.getLocale());
		} else if (!validateRoomType(roomType).equals("")) {
			messageReturn = validateRoomType(roomType);
		} else {
			roomTypeDao.save(roomType);
		}

		// Si en caso de tener un detalle de precios, entonces no se toma la principal?
		// es una o la otra?

		return messageReturn;
	}
	
	
	/*
	 * Método que se encarga de actualizar un tipo de habitación
	 * @return String
	 */
	public String update(RoomType roomType) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if (StringUtils.isBlank(roomType.getUuid())) {
			messageReturn = messageSource.getMessage("roomtype.id", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(roomType.getHotelId())) {
			messageReturn = messageSource.getMessage("roomtype.hotelId", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(roomType.getDescription())) {
			messageReturn = messageSource.getMessage("roomtype.description", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(roomType.getPriceDay().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_day", null, LocaleContextHolder.getLocale());
		} else if (regularExpression.validateNumeric(roomType.getPriceDay().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_day_numeric", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(roomType.getPriceHour().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_hour", null, LocaleContextHolder.getLocale());
		} else if (regularExpression.validateNumeric(roomType.getPriceHour().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_hour_numeric", null, LocaleContextHolder.getLocale());
		} else if (!validateRoomType(roomType).equals("")) {
			messageReturn = validateRoomType(roomType);
		} else {
			roomTypeDao.update(roomType);
		}
		
		// Si en caso de tener un detalle de precios, entonces no se toma la principal?
		// es una o la otra?
		
		return messageReturn;
	}

		
	/*
	 * Método que se encarga de mostrar un tipo de habitación por hotel
	 * @return RoomType
	 */
	public RoomType findByHotelIdAndRoomType(String hotelId, String roomTypeId) throws MongoException, Exception {

		RoomType roomType = roomTypeDao.findByHotelIdAndRoomType(hotelId, roomTypeId);

		return roomType;

	}

	
	/*
	 * Método que se encarga de listar los tipos de habitaciones de un hotel
	 * @return
	 */
	public List<RoomType> findAll(String hotelId) throws MongoException, Exception {

		List<RoomType> roomTypes = roomTypeDao.findAll(hotelId);

		return roomTypes;

	}

	
	private String validateRoomType(RoomType roomType) {
		
		PriceDetails priceDetails = null;
		
		String messageReturn = "";
		
		try {
			
			if(roomType != null && roomType.getPriceDetails() != null) {
				for(int a = 0; a < roomType.getPriceDetails().size(); a++) {
					priceDetails = roomType.getPriceDetails().get(a);
					
					if(StringUtils.isBlank(priceDetails.getDay())){
						messageReturn = messageSource.getMessage("roomtype.day" + " - Detalle Nro " + (a + 1), null, LocaleContextHolder.getLocale());
					}else if(priceDetails.getPriceDay() == null) {
						messageReturn = messageSource.getMessage("roomtype.price_day" + " - Detalle Nro " + (a + 1), null, LocaleContextHolder.getLocale());
					}else if(regularExpression.validateNumeric(priceDetails.getPriceDay().toString())){
						messageReturn = messageSource.getMessage("roomtype.price_day_numeric" + " - Detalle Nro " + (a + 1), null, LocaleContextHolder.getLocale());
					}else if(priceDetails.getPriceHour() == null) {
						messageReturn = messageSource.getMessage("roomtype.price_hour" + " - Detalle Nro " + (a + 1), null, LocaleContextHolder.getLocale());
					}else if(regularExpression.validateNumeric(priceDetails.getPriceHour().toString())){
						messageReturn = messageSource.getMessage("roomtype.price_hour_numeric" + " - Detalle Nro " + (a + 1), null, LocaleContextHolder.getLocale());
					}else if((priceDetails.getPriceHour() * 24) > priceDetails.getPriceDay()){
						messageReturn = messageSource.getMessage("roomtype.price_hour_and_day" + " - Detalle Nro " + (a + 1), null, LocaleContextHolder.getLocale());
					}
					
					
					/*private String day;
					private Double priceDay;
					private Double priceHour;
					private Integer startTime;
					private Integer endTime;
					private Boolean holiday;
						*/	
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return messageReturn;
	}
}
