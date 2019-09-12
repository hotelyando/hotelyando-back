package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.IItemDao;
import co.com.hotelyando.database.model.Item;

@Service
public class ItemService implements IItemService {

	@Autowired
	private IItemDao iItemDao;
	
	@Override
	public String registrarItem(Item item) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = iItemDao.registrarItem(item);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
	}

	@Override
	public List<Item> consultarItemsPorHotel(Integer hotelId) {
		
		List<Item> items = null;
		
		try {
			
			items = iItemDao.consultarItemsPorHotel(hotelId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return items;
	}

	@Override
	public Item consultarItemPorHotel(Integer hotelId, Integer itemId) {
		
		Item item = null;
		
		try {
			
			item = iItemDao.consultarItemPorHotel(hotelId, itemId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}

}
