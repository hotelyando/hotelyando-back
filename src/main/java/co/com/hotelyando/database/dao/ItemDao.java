package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.repository.IItemRepository;

@Repository
public class ItemDao implements IItemDao{
	
	@Autowired
	private IItemRepository iItemRepository;
	
	@Override
	public String registrarItem(Item item) throws Exception {
		
		iItemRepository.save(item);
		
		return null;
	}

	@Override
	public List<Item> consultarItemsPorHotel(Integer hotelId) throws Exception {
		
		List<Item> items = null;
		
		items = iItemRepository.findByHotelId(hotelId);
		
		return items;
	}

	@Override
	public Item consultarItemPorHotel(Integer hotelId, Integer itemId) throws Exception {
		
		Item item = null;
		
		item = iItemRepository.findByHotelIdAndItemId(hotelId, itemId);
		
		return item;
	}

}
