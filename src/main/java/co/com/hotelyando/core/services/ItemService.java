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
	
	public String registrarItem(Item item) throws Exception {
		
		String retornoMensaje = "";
			
		retornoMensaje = itemDao.registrarItem(item);
			
		return retornoMensaje;
	}

	public List<Item> consultarItemsPorHotel(Integer hotelId) throws Exception {
		
		List<Item> items = null;
		items = itemDao.consultarItemsPorHotel(hotelId);
			
		return items;
	}

	public Item consultarItemPorHotel(Integer hotelId, Integer itemId) throws Exception {
		
		Item item = null;
		item = itemDao.consultarItemPorHotel(hotelId, itemId);
		
		return item;
	}

}
