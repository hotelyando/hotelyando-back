package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.repository.IItemRepository;

@Repository
public class ItemDao {
	
private final IItemRepository iItemRepository;
	
	public ItemDao(IItemRepository iItemRepository) {
		this.iItemRepository = iItemRepository;
	}
	
	public String save(Item item) throws Exception {
		
		iItemRepository.save(item);
		
		return "Ok";
	}

	
	public List<Item> findByHotelId(String hotelId) throws Exception {
		
		List<Item> items = null;
		
		items = iItemRepository.findByHotelId(hotelId);
		
		return items;
	}

	
	public Item findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Item item = null;
		
		item = iItemRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return item;
	}


}
