package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.repository.IRoomRepository;

@Repository
public class RoomDao{
	
	private final IRoomRepository iRoomRepository;
	
	public RoomDao(IRoomRepository iRoomRepository) {
		this.iRoomRepository = iRoomRepository;
	}
	
	
	public void save(Room room) throws MongoException, Exception {
		iRoomRepository.save(room);
	}
	
	public void update(Room room) throws MongoException, Exception {
		iRoomRepository.save(room);
	}
	
	public void delete(String uuid) throws MongoException, Exception {
		iRoomRepository.deleteById(uuid);
	}

	@Cacheable("findByHotelId")
	public List<Room> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Room> rooms = iRoomRepository.findByHotelId(hotelId);
		
		return rooms;
	}
	
	@Cacheable("findByHotelIdAndUuid")
	public Room findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Room room = iRoomRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return room;
	}
	
	public Room findByHotelIdAndDescription(String hotelId, String description) throws MongoException, Exception {
		
		Room room = iRoomRepository.findByHotelIdAndDescription(hotelId, description);
		
		return room;
	}

}
