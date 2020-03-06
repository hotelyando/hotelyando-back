package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.repository.IItemRepository;

@Repository
public class ItemDao {
	
private final IItemRepository iItemRepository;
	
	public ItemDao(IItemRepository iItemRepository) {
		this.iItemRepository = iItemRepository;
	}
	
	public void save(Item item) throws MongoException, Exception {
		iItemRepository.save(item);
	}

	public void update(Item item) throws MongoException, Exception {
		iItemRepository.save(item);
	}
	
	public List<Item> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Item> items = null;
		items = iItemRepository.findByHotelId(hotelId);
		
		return items;
	}
	
	public Item findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Item item = null;
		item = iItemRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return item;
	}
	
	public Item findByHotelIdAndName(String hotelId, String name) throws MongoException, Exception {
		
		Item item = null;
		item = iItemRepository.findByHotelIdAndName(hotelId, name);
		
		return item;
	}


}
