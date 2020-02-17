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
	
	public String registrarItem(Item item, User user) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = itemService.registrarItem(item);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Item> consultarItemsPorHotel(User user) {
		
		List<Item> items = null;
		
		try {
			
			items = itemService.consultarItemsPorHotel(user.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return items;
	}

	public Item consultarItemPorHotel(User user, String itemId) {
		
		Item item = null;
		
		try {
			
			item = itemService.consultarItemPorHotel(user.getHotelId(), itemId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
}
