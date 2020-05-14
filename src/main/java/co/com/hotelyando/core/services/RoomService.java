package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.RoomDao;
import co.com.hotelyando.database.model.Room;

@Service
public class RoomService {

	@Autowired
	private MessageSource messageSource;
	
	private RoomDao roomDao;
	
	private RegularExpression regularExpression;
	
	public RoomService(RoomDao roomDao) {
		this.roomDao = roomDao;
		
		regularExpression = new RegularExpression();
	}
	
	
	/*
	 * M�todo que registrar una habitaci�n del hotel
	 * @return String 
	 */
	public String save(Room room) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(room.getUuid())) {
			messageReturn = messageSource.getMessage("room.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getHotelId())) {
			messageReturn = messageSource.getMessage("room.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getDescription())) {
			messageReturn = messageSource.getMessage("room.description", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateSpecialCharacters(room.getDescription())) {
			messageReturn = messageSource.getMessage("room.description_characters", null, LocaleContextHolder.getLocale());
		}else if(findByHotelIdAndDescription(room.getHotelId(), room.getDescription()) != null) {
			messageReturn = messageSource.getMessage("room.description_unique", null, LocaleContextHolder.getLocale());
		}else if(room.getFloor() < 1 ) {
			messageReturn = messageSource.getMessage("room.floor", null, LocaleContextHolder.getLocale());
		}else if(room.getArea() < 1 ) {
			messageReturn = messageSource.getMessage("room.area", null, LocaleContextHolder.getLocale());
		}else if(room.getMaximumPersons() < 0) {
			messageReturn = messageSource.getMessage("room.maximum_persons", null, LocaleContextHolder.getLocale());
		}else if(room.getNumberBeds() < 0) {
			messageReturn = messageSource.getMessage("room.number_beds", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getFreeParking().toString())) {
			messageReturn = messageSource.getMessage("room.free_parking", null, LocaleContextHolder.getLocale());
		}else if(validateState(room.getState())) {
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
	 * M�todo que actualiza una habitaci�n del hotel
	 * @return String 
	 */
	public String update(Room room) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(room.getUuid())) {
			messageReturn = messageSource.getMessage("room.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getHotelId())) {
			messageReturn = messageSource.getMessage("room.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getDescription())) {
			messageReturn = messageSource.getMessage("room.description", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateSpecialCharacters(room.getDescription())) {
			messageReturn = messageSource.getMessage("room.description_characters", null, LocaleContextHolder.getLocale());
		}else if(findByHotelIdAndDescription(room.getHotelId(), room.getDescription()) != null) {
			messageReturn = messageSource.getMessage("room.description_unique", null, LocaleContextHolder.getLocale());
		}else if(room.getFloor() < 1 ) {
			messageReturn = messageSource.getMessage("room.floor", null, LocaleContextHolder.getLocale());
		}else if(room.getArea() < 1 ) {
			messageReturn = messageSource.getMessage("room.area", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getMaximumPersons().toString())) {
			messageReturn = messageSource.getMessage("room.maximum_persons", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getNumberBeds().toString())) {
			messageReturn = messageSource.getMessage("room.number_beds", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getFreeParking().toString())) {
			messageReturn = messageSource.getMessage("room.free_parking", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(room.getRoomType())) {
			messageReturn = messageSource.getMessage("room.type", null, LocaleContextHolder.getLocale());
		}else if(validateState(room.getState())) {
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
	 * M�todo que retorna una lista de habitaciones de un hotel
	 * @List<Room>
	 */
	public List<Room> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Room> rooms = roomDao.findByHotelId(hotelId);
		
		return rooms;
	}

	
	/*
	 * M�todo que retorna una habitaci�n de hotel
	 * @Room
	 */
	public Room findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Room room = roomDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return room;
	}
	
	
	public String delete(String uuid) throws MongoException, Exception {
		
		String messageReturn = "";
		
		roomDao.delete(uuid);
		
		return messageReturn;
	}
	
	
	public Room findByHotelIdAndDescription(String hotelId, String description) throws MongoException, Exception {
		
		Room room = roomDao.findByHotelIdAndDescription(hotelId, description);
		
		return room;
	}
	
	
	public Boolean validateState(String state) {
		
		Boolean stateReturn = true;
		
		if(!StringUtils.isBlank(state)) {
			for(int a = 0; a < PrintVariable.STATES_ROOM.length; a++) {
				if(state.equals(PrintVariable.STATES_ROOM[a])) {
					stateReturn = false;
					break;
				}
			}
		}
		
		return stateReturn;
	}

}
