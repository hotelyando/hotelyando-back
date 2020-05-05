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
	 * M�todo que registra un item en Mongo
	 * @return void
	 */
	public void save(Item item) throws MongoException, Exception {
		iItemRepository.save(item);
	}

	
	/*
	 * M�todo que actualiza un item en Mongo
	 * @return void
	 */
	public void update(Item item) throws MongoException, Exception {
		iItemRepository.save(item);
	}
	
	
	/*
	 * Método que elimina un item
	 * @return void
	 */
	public void delete(String uuid) throws MongoException, Exception {
		iItemRepository.deleteById(uuid);
	}
	
	/*
	 * M�todo que retorna todos los item�s de un hotel
	 * @return List<Item>
	 */
	public List<Item> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Item> items = iItemRepository.findByHotelId(hotelId);
		
		return items;
	}
	
	
	/*
	 * M�todo que retorna un item por Id y por hotel
	 * @return Item
	 */
	public Item findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Item item = iItemRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return item;
	}
	
	
	/*
	 * M�todo que retorna un item por nombre y por hotel 
	 * @return Item
	 */
	public Item findByHotelIdAndName(String hotelId, String name) throws MongoException, Exception {
		
		Item item = iItemRepository.findByHotelIdAndName(hotelId, name);
		
		return item;
	}
	
}
