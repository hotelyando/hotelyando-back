package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.repository.IRoomRepository;

@Repository
public class RoomDao{
	
	private final IRoomRepository iRoomRepository;
	
	public RoomDao(IRoomRepository iRoomRepository) {
		this.iRoomRepository = iRoomRepository;
	}
	
	
	public void save(Room room) throws Exception {
		iRoomRepository.save(room);
	}
	
	public void update(Room room) throws Exception {
		iRoomRepository.save(room);
	}

	public List<Room> findByHotelId(String hotelId) throws Exception {
		
		List<Room> rooms = null;
		rooms = iRoomRepository.findByHotelId(hotelId);
		
		return rooms;
	}

	public Room findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Room room = null;
		room = iRoomRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return room;
	}

}
