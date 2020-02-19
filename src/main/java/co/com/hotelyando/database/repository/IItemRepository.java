package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Item;

@JaversSpringDataAuditable
public interface IItemRepository extends MongoRepository<Item, String> {
	
	List<Item> findByHotelId(String hotelId);
	Item findByHotelIdAndUuid(String hotelId, String uuid);
	

}
