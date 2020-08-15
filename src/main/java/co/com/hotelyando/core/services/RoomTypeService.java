package co.com.hotelyando.core.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	 * M�todo que se encarga de registrar un tipo de habitaci�n
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
		} else if (findByHotelIdAndDescription(roomType.getHotelId(), roomType.getDescription())  != null) {
			messageReturn = messageSource.getMessage("roomtype.description", null, LocaleContextHolder.getLocale());
		} else if (roomType.getPriceDay() == null) {
			messageReturn = messageSource.getMessage("roomtype.price_day", null, LocaleContextHolder.getLocale());
		} else if (regularExpression.validateNumeric(roomType.getPriceDay().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_day_numeric", null, LocaleContextHolder.getLocale());
		} else if (roomType.getPriceHour() == null) {
			messageReturn = messageSource.getMessage("roomtype.price_hour", null, LocaleContextHolder.getLocale());
		} else if (regularExpression.validateNumeric(roomType.getPriceHour().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_hour_numeric", null, LocaleContextHolder.getLocale());
		} else if (roomType.getPriceHour() > roomType.getPriceDay()) {
			messageReturn = messageSource.getMessage("roomtype.price_hour_and_day", null, LocaleContextHolder.getLocale());
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
	 * M�todo que se encarga de actualizar un tipo de habitaci�n
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
		/*} else if (findByHotelIdAndDescription(roomType.getHotelId(), roomType.getDescription())  != null) {
			messageReturn = messageSource.getMessage("roomtype.description", null, LocaleContextHolder.getLocale());*/
		} else if (roomType.getPriceDay() == null) {
			messageReturn = messageSource.getMessage("roomtype.price_day", null, LocaleContextHolder.getLocale());
		} else if (regularExpression.validateNumeric(roomType.getPriceDay().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_day_numeric", null, LocaleContextHolder.getLocale());
		} else if (roomType.getPriceHour() == null) {
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
	 * M�todo que se encarga de mostrar un tipo de habitaci�n por hotel
	 * @return RoomType
	 */
	public RoomType findByHotelIdAndRoomType(String hotelId, String roomTypeId) throws MongoException, Exception {

		RoomType roomType = roomTypeDao.findByHotelIdAndRoomType(hotelId, roomTypeId);

		return roomType;

	}

	
	/*
	 * M�todo que se encarga de listar los tipos de habitaciones de un hotel
	 * @return
	 */
	public List<RoomType> findAll(String hotelId) throws MongoException, Exception {

		List<RoomType> roomTypes = roomTypeDao.findAll(hotelId);

		return roomTypes;

	}

	
	private String validateRoomType(RoomType roomType) {
		
		PriceDetails priceDetails = null;
		
		String messageReturn = "";
		
		List<String> days = new ArrayList<String>();
		
		try {
			
			if(roomType != null && roomType.getPriceDetails() != null) {
				for(int a = 0; a < roomType.getPriceDetails().size(); a++) {
					priceDetails = roomType.getPriceDetails().get(a);
					
					if(StringUtils.isBlank(priceDetails.getDay())){
						messageReturn = "Debe ingresar el día de la semana." + " Detalle Nro " + (a + 1);
					}else if(priceDetails.getPriceDay() == null) {
						messageReturn = "Debe ingresar el valor del día." + " Detalle Nro " + (a + 1);
					}else if(priceDetails.getPriceDay() < 1){
						messageReturn = "El valor debe ser númerico y mayor o igual a cero." + " Detalle Nro " + (a + 1);
					}else if(priceDetails.getPriceHour() == null) {
						messageReturn = "Debe ingresar el valor de la hora." + " Detalle Nro " + (a + 1);
					}else if(priceDetails.getPriceHour() < 1){
						messageReturn = "El valor debe ser númerico y mayor o igual a cero." + " Detalle Nro " + (a + 1);
					}else if(priceDetails.getPriceHour() > priceDetails.getPriceDay()){
						messageReturn = "El valor de la hora no puede ser mayor al valor día." + " - Detalle Nro " + (a + 1);
					}else {
						days.add(priceDetails.getDay());
					}
				}
				
				if(messageReturn.equals("") && days.size() > 0) {
					
					Set<String> set = new HashSet<>();
					Set<String> repeat = days.stream()
										.filter(n -> !set.add(n))
										.collect(Collectors.toSet());
					if(repeat.size() > 0) {
						messageReturn = "Solo puede haber un día por tipo de item.";
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return messageReturn;
	}
	
	
	public String delete(String uuid) throws MongoException, Exception {

		String messageReturn = "";
		
		roomTypeDao.delete(uuid);
		
		return messageReturn;
		
	}
	
	
	public RoomType findByHotelIdAndDescription(String hotelId, String description) throws MongoException, Exception {
		
		RoomType roomType = roomTypeDao.findByHotelIdAndDescription(hotelId, description);
		
		return roomType;
		
	}
}
