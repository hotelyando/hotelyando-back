package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.ItemDao;
import co.com.hotelyando.database.model.Item;

@Service
public class ItemService {
	
	private final ItemDao itemDao;
	
	public ItemService(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public String save(Item item) throws Exception {
		
		String messageReturn = "";
			
		messageReturn = itemDao.save(item);
			
		return messageReturn;
	}

	public List<Item> findByHotelId(String hotelId) throws Exception {
		
		List<Item> items = null;
		items = itemDao.findByHotelId(hotelId);
			
		return items;
	}

	public Item findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Item item = null;
		item = itemDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return item;
	}

}
