package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.database.dao.RoomDao;
import co.com.hotelyando.database.model.Room;

@Service
public class RoomService {

	private RoomDao roomDao;
	
	public RoomService(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
	
	
	public String save(Room room) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(room.getUuid())) {
			messageReturn = PrintEntity.HABITACION_VALIDACION_ID;
		}else if(StringUtils.isBlank(room.getHotelId())) {
			messageReturn = PrintEntity.HABITACION_HOTEL_ID;
		}else if(StringUtils.isBlank(room.getCheckIn())) {
			messageReturn = PrintEntity.HABITACION_CHECK_IN;
		}else if(StringUtils.isBlank(room.getCheckOut())) {
			messageReturn = PrintEntity.HABITACION_CHECK_OUT;
		}else if(StringUtils.isBlank(room.getType())) {
			messageReturn = PrintEntity.HABITACION_TIPO;
		}else {
			roomDao.save(room);
		}
		
		return messageReturn;
	}
	
	
	public String update(Room room) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(room.getUuid())) {
			messageReturn = PrintEntity.HABITACION_VALIDACION_ID;
		}else if(StringUtils.isBlank(room.getHotelId())) {
			messageReturn = PrintEntity.HABITACION_HOTEL_ID;
		}else if(StringUtils.isBlank(room.getCheckIn())) {
			messageReturn = PrintEntity.HABITACION_CHECK_IN;
		}else if(StringUtils.isBlank(room.getCheckOut())) {
			messageReturn = PrintEntity.HABITACION_CHECK_OUT;
		}else if(StringUtils.isBlank(room.getType())) {
			messageReturn = PrintEntity.HABITACION_TIPO;
		}else {
			roomDao.update(room);
		}
		
		return messageReturn;
	}

	public List<Room> findByHotelId(String hotelId) throws Exception {
		
		List<Room> rooms = null;
		rooms = roomDao.findByHotelId(hotelId);
		
		return rooms;
	}

	public Room findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Room room = null;
		room = roomDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return room;
	}

}
