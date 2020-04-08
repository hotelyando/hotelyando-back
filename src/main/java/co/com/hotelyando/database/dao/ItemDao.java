package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.repository.IItemRepository;

@Repository
public class ItemDao {
	
private final IItemRepository iItemRepository;
	
	public ItemDao(IItemRepository iItemRepository) {
		this.iItemRepository = iItemRepository;
	}
	
	
	/*
	 * Método que registra un item en Mongo
	 * @return void
	 */
	public void save(Item item) throws MongoException, Exception {
		iItemRepository.save(item);
	}

	
	/*
	 * Método que actualiza un item en Mongo
	 * @return void
	 */
	public void update(Item item) throws MongoException, Exception {
		iItemRepository.save(item);
	}
	
	
	/*
	 * Método que retorna todos los item´s de un hotel
	 * @return List<Item>
	 */
	public List<Item> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Item> items = iItemRepository.findByHotelId(hotelId);
		
		return items;
	}
	
	
	/*
	 * Método que retorna un item por Id y por hotel
	 * @return Item
	 */
	public Item findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Item item = iItemRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return item;
	}
	
	
	/*
	 * Método que retorna un item por nombre y por hotel 
	 * @return Item
	 */
	public Item findByHotelIdAndDescription(String hotelId, String description) throws MongoException, Exception {
		
		Item item = iItemRepository.findByHotelIdAndDescription(hotelId, description);
		
		return item;
	}
	
}
