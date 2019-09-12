package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Item;

public interface IItemRepository extends MongoRepository<Item, Integer> {
	
	List<Item> findByHotelId(Integer hotelId);
	Item findByHotelIdAndItemId(Integer hotelId, Integer itemId);
	

}
