package co.com.hotelyando.core.services;

import java.util.List;

import co.com.hotelyando.database.model.Item;

public interface IItemService {
	
	String registrarItem(Item item);
	List<Item> consultarItemsPorHotel(Integer hotelId);
	Item consultarItemPorHotel(Integer hotelId, Integer itemId);

}
