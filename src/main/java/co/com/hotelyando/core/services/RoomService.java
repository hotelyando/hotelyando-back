package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.database.dao.RoomDao;
import co.com.hotelyando.database.model.Room;

@Service
public class RoomService {

	@Autowired
	private MessageSource messageSource;
	
	private String messageReturn = "";
	
	private RoomDao roomDao;
	
	public RoomService(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
	
	
	/*
	 * Método que registrar una habitación del hotel
	 * @return String 
	 */
	public String save(Room room) throws MongoException, Exception {
		
		if(StringUtils.isBlank(room.getUuid())) {
			messageReturn = messageSource.getMessage("room.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getHotelId())) {
			messageReturn = messageSource.getMessage("room.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getDescription())) {
			messageReturn = messageSource.getMessage("room.description", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getFloor().toString())) {
			messageReturn = messageSource.getMessage("room.floor", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getArea().toString())) {
			messageReturn = messageSource.getMessage("room.area", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getMaximumPersons().toString())) {
			messageReturn = messageSource.getMessage("room.maximum_persons", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getNumberBeds().toString())) {
			messageReturn = messageSource.getMessage("room.number_beds", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getFreeParking().toString())) {
			messageReturn = messageSource.getMessage("room.free_parking", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getRoomType())) {
			messageReturn = messageSource.getMessage("room.type", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getState())) {
			messageReturn = messageSource.getMessage("room.state", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getScore().toString())) {
			messageReturn = messageSource.getMessage("room.score", null, LocaleContextHolder.getLocale());
		}else if(room.getComments() == null) {
			messageReturn = messageSource.getMessage("room.comments", null, LocaleContextHolder.getLocale());
		}else {
			roomDao.save(room);
		}
			
		return messageReturn;
	}
	
	
	/*
	 * Método que actualiza una habitación del hotel
	 * @return String 
	 */
	public String update(Room room) throws MongoException, Exception {
		
		if(StringUtils.isBlank(room.getUuid())) {
			messageReturn = messageSource.getMessage("room.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getHotelId())) {
			messageReturn = messageSource.getMessage("room.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getDescription())) {
			messageReturn = messageSource.getMessage("room.description", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getFloor().toString())) {
			messageReturn = messageSource.getMessage("room.floor", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getArea().toString())) {
			messageReturn = messageSource.getMessage("room.area", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getMaximumPersons().toString())) {
			messageReturn = messageSource.getMessage("room.maximum_persons", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getNumberBeds().toString())) {
			messageReturn = messageSource.getMessage("room.number_beds", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getFreeParking().toString())) {
			messageReturn = messageSource.getMessage("room.free_parking", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getRoomType())) {
			messageReturn = messageSource.getMessage("room.type", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getState())) {
			messageReturn = messageSource.getMessage("room.state", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getScore().toString())) {
			messageReturn = messageSource.getMessage("room.score", null, LocaleContextHolder.getLocale());
		}else if(room.getComments() == null) {
			messageReturn = messageSource.getMessage("room.comments", null, LocaleContextHolder.getLocale());
		}else {
			roomDao.update(room);
		}
		
		return messageReturn;
	}

	
	/*
	 * Método que retorna una lista de habitaciones de un hotel
	 * @List<Room>
	 */
	public List<Room> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Room> rooms = roomDao.findByHotelId(hotelId);
		
		return rooms;
	}

	
	/*
	 * Método que retorna una habitación de hotel
	 * @Room
	 */
	public Room findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Room room = roomDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return room;
	}

}
