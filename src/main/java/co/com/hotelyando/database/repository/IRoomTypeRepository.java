package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.RoomType;

public interface IRoomTypeRepository extends MongoRepository<RoomType, Integer> {
	
	List<RoomType> findByHotelId(String hotelId);

}
