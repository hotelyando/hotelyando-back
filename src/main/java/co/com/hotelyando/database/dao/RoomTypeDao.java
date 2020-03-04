package co.com.hotelyando.database.dao;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.RoomType;
import co.com.hotelyando.database.repository.IRoomTypeRepository;

@Repository
public class RoomTypeDao{
	
	private final IRoomTypeRepository iRoomTypeRepository;
	
	public RoomTypeDao(IRoomTypeRepository iRoomTypeRepository) {
		this.iRoomTypeRepository = iRoomTypeRepository;
	}
	
	
	public void save(RoomType roomType) throws Exception {
		iRoomTypeRepository.save(roomType);
	}

	public void update(RoomType roomType) throws Exception {
		iRoomTypeRepository.save(roomType);
	}

}
