package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.ItemService;
import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.model.Usuario;

@Service
public class ItemBusiness {
	
	private final ItemService itemService;
	
	public ItemBusiness(ItemService itemService) {
		this.itemService = itemService;
	}
	
	public String registrarItem(Item item, Usuario usuario) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = itemService.registrarItem(item);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Item> consultarItemsPorHotel(Usuario usuario) {
		
		List<Item> items = null;
		
		try {
			
			items = itemService.consultarItemsPorHotel(usuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return items;
	}

	public Item consultarItemPorHotel(Usuario usuario, String itemId) {
		
		Item item = null;
		
		try {
			
			item = itemService.consultarItemPorHotel(usuario.getHotelId(), itemId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
}
