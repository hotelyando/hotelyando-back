package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.RoomType;

@JaversSpringDataAuditable
public interface IRoomTypeRepository extends MongoRepository<RoomType, Integer> {

	public RoomType findByHotelIdAndUuid(String hotelId, String roomTypeId);
	public List<RoomType> findByHotelId(String hotelId);

}
