package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.database.dao.RoomTypeDao;
import co.com.hotelyando.database.model.RoomType;

@Service
public class RoomTypeService {

	@Autowired
	private MessageSource messageSource;

	private RoomTypeDao roomTypeDao;

	private String messageReturn = "";
	
	public RoomTypeService(RoomTypeDao roomTypeDao) {
		this.roomTypeDao = roomTypeDao;
	}

	
	/*
	 * Método que se encarga de registrar un tipo de habitación
	 * @return String
	 */
	public String save(RoomType tipoRoomType) throws MongoException, Exception {

		if (StringUtils.isBlank(tipoRoomType.getUuid())) {
			messageReturn = messageSource.getMessage("roomtype.id", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(tipoRoomType.getHotelId())) {
			messageReturn = messageSource.getMessage("roomtype.hotelId", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(tipoRoomType.getDescription())) {
			messageReturn = messageSource.getMessage("roomtype.description", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(tipoRoomType.getPriceDay().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_day", null, LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(tipoRoomType.getPriceHour().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_hour", null, LocaleContextHolder.getLocale());
		} else {
			roomTypeDao.save(tipoRoomType);
		}

		// Si en caso de tener un detalle de precios, entonces no se toma la principal?
		// es una o la otra?

		return messageReturn;
	}
	
	
	/*
	 * Método que se encarga de actualizar un tipo de habitación
	 * @return String
	 */
	public String update(RoomType tipoRoomType) throws MongoException, Exception {
		
		if(StringUtils.isBlank(tipoRoomType.getUuid())) {
			messageReturn = messageSource.getMessage("roomtype.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(tipoRoomType.getHotelId())) {
			messageReturn = messageSource.getMessage("roomtype.hotelId", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(tipoRoomType.getDescription())) {
			messageReturn = messageSource.getMessage("roomtype.description", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(tipoRoomType.getPriceDay().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_day", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(tipoRoomType.getPriceHour().toString())) {
			messageReturn = messageSource.getMessage("roomtype.price_hour", null, LocaleContextHolder.getLocale());
		}else {
			roomTypeDao.update(tipoRoomType);
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

}
