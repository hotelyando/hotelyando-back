package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.ItemService;
import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.model.User;

@Service
public class ItemBusiness {
	
	private final ItemService itemService;
	
	public ItemBusiness(ItemService itemService) {
		this.itemService = itemService;
	}
	
	public String save(Item item, User user) {
		
		String messageReturn = "";
		
		try {
			messageReturn = itemService.save(item);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return messageReturn;
	}

	public List<Item> findByHotelId(User user) {
		
		List<Item> items = null;
		
		try {
			
			items = itemService.findByHotelId(user.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return items;
	}

	public Item findByHotelIdAndUuid(User user, String uuid) {
		
		Item item = null;
		
		try {
			
			item = itemService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
}
