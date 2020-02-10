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
	
	public String registrarItem(Item item) throws Exception {
		
		iItemRepository.save(item);
		
		return "Ok";
	}

	
	public List<Item> consultarItemsPorHotel(String hotelId) throws Exception {
		
		List<Item> items = null;
		
		items = iItemRepository.findByHotelId(hotelId);
		
		return items;
	}

	
	public Item consultarItemPorHotel(String hotelId, String itemId) throws Exception {
		
		Item item = null;
		
		item = iItemRepository.findByHotelIdAndItemId(hotelId, itemId);
		
		return item;
	}


}
