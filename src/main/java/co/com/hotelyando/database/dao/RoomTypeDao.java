package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.RoomType;
import co.com.hotelyando.database.repository.IRoomTypeRepository;

@Repository
public class RoomTypeDao{
	
	private final IRoomTypeRepository iRoomTypeRepository;
	
	public RoomTypeDao(IRoomTypeRepository iRoomTypeRepository) {
		this.iRoomTypeRepository = iRoomTypeRepository;
	}
	
	
	public void save(RoomType roomType) throws MongoException, Exception {
		iRoomTypeRepository.save(roomType);
	}

	public void update(RoomType roomType) throws MongoException, Exception {
		iRoomTypeRepository.save(roomType);
	}
	
	public void delete(String uuid) throws MongoException, Exception {
		iRoomTypeRepository.deleteById(uuid);
	}

	public RoomType findByHotelIdAndRoomType(String hotelId, String roomTypeId) throws MongoException, Exception {
		
		RoomType roomType = iRoomTypeRepository.findByHotelIdAndUuid(hotelId, roomTypeId);
		
		return roomType;
		
	}
	
	public List<RoomType> findAll(String hotelId) throws MongoException, Exception {
		
		List<RoomType> roomTypes = iRoomTypeRepository.findByHotelId(hotelId);
		
		return roomTypes;
		
	}
}
