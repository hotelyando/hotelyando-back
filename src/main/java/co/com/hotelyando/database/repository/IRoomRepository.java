package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Room;

@JaversSpringDataAuditable
public interface IRoomRepository extends MongoRepository<Room, String> {
	
	List<Room> findByHotelId(String hotelId);
	Room findByHotelIdAndUuid(String hotelId, String uuid);
	

}
