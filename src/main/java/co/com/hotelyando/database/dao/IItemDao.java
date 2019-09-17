package co.com.hotelyando.database.dao;

import java.util.List;

import co.com.hotelyando.database.model.Item;

public interface IItemDao {
	
	String registrarItem(Item item) throws Exception;
	List<Item> consultarItemsPorHotel(Integer hotelId) throws Exception;
	Item consultarItemPorHotel(Integer hotelId, Integer itemId) throws Exception;

}
