package co.com.hotelyando.core.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.ItemDao;
import co.com.hotelyando.database.model.Item;

@Service
public class ItemService {
	
	@Autowired
	private MessageSource messageSource;
	
	private RegularExpression regularExpression = null;
	
	private final ItemDao itemDao;
	
	private String messageReturn = "";
	
	public ItemService(ItemDao itemDao) {
		this.itemDao = itemDao;
		
		regularExpression = new RegularExpression();
	}
	
	
	/*
	 * Método para el registro de un ITEM del hotel
	 * @return String
	 */
	public String save(Item item) throws MongoException, Exception {
		
		if(StringUtils.isBlank(item.getUuid())) {
			messageReturn = messageSource.getMessage("item.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(item.getHotelId())) {
			messageReturn = messageSource.getMessage("item.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(item.getActive() == null) {
			messageReturn = messageSource.getMessage("item.active", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateNumeric(item.getQuantity().toString())) {
			messageReturn = messageSource.getMessage("item.quantity", null, LocaleContextHolder.getLocale());
		}else if(item.getQuantity() < 0 ) {
			messageReturn = messageSource.getMessage("item.quantity_number", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(item.getDescription())) {
			messageReturn = messageSource.getMessage("item.description", null, LocaleContextHolder.getLocale());
		}else if(item.getPrice() == null) {
			messageReturn = messageSource.getMessage("item.price", null, LocaleContextHolder.getLocale());
		}else if(item.getPrice() < 0) {
			messageReturn = messageSource.getMessage("item.price_number", null, LocaleContextHolder.getLocale());
		}else if(item.getStock() == null) {
			messageReturn = messageSource.getMessage("item.stock", null, LocaleContextHolder.getLocale());
		}else if(item.getStock() < 0) {
			messageReturn = messageSource.getMessage("item.stock_number", null, LocaleContextHolder.getLocale());
		}else if(nameValidate(item.getHotelId(), item.getDescription(), false)) {
			messageReturn = messageSource.getMessage("item.name_unique", null, LocaleContextHolder.getLocale());
		}else {
			itemDao.save(item);
		}
		
		//Hay item de Hotel e Item de habitación?, si es así, se debería colocar un tipo de item?
		
		return messageReturn;
	}
	
	
	/*
	 * Método para la actualización de un ITEM del hotel
	 * @return String
	 */
	public String update(Item item) throws MongoException, Exception {
		
		if(StringUtils.isBlank(item.getUuid())) {
			messageReturn = messageSource.getMessage("item.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(item.getHotelId())) {
			messageReturn = messageSource.getMessage("item.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(item.getActive() == null) {
			messageReturn = messageSource.getMessage("item.active", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateNumeric(item.getQuantity().toString())) {
			messageReturn = messageSource.getMessage("item.quantity", null, LocaleContextHolder.getLocale());
		}else if(item.getQuantity() < 0 ) {
			messageReturn = messageSource.getMessage("item.quantity_number", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(item.getDescription())) {
			messageReturn = messageSource.getMessage("item.description", null, LocaleContextHolder.getLocale());
		}else if(item.getPrice() == null) {
			messageReturn = messageSource.getMessage("item.price", null, LocaleContextHolder.getLocale());
		}else if(item.getPrice() < 0) {
			messageReturn = messageSource.getMessage("item.price_number", null, LocaleContextHolder.getLocale());
		}else if(item.getStock() == null) {
			messageReturn = messageSource.getMessage("item.stock", null, LocaleContextHolder.getLocale());
		}else if(item.getStock() < 0) {
			messageReturn = messageSource.getMessage("item.stock_number", null, LocaleContextHolder.getLocale());
		}else if(nameValidate(item.getHotelId(), item.getDescription(), true)) {
			messageReturn = messageSource.getMessage("item.name_unique", null, LocaleContextHolder.getLocale());
		}else {
			itemDao.update(item);
		}
		
		//Hay item de Hotel e Item de habitación?, si es así, se debería colocar un tipo de item?
		
		return messageReturn;
	}

	
	/*
	 * Método para que liste todos los ITEM'S de un hotel
	 * @return List<Item>
	 */
	public List<Item> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Item> items = itemDao.findByHotelId(hotelId);
			
		return items;
	}
	
	
	/*
	 * Método para que liste todos los ITEM'S de un hotel
	 * @return List<Item>
	 */
	public List<Item> findByHotelId(String hotelId, String description) throws MongoException, Exception {
		
		List<Item> items = new ArrayList<Item>();
		
		if(description.equals("")) {
			items = itemDao.findByHotelId(hotelId);
		}else {
			Item item = itemDao.findByHotelIdAndDescription(hotelId, description);
			
			if(item != null) {
				items.add(item);
			}
		}
			
		return items;
	}
	/*
	 * Método para que liste un ITEM del hotel por uuid
	 * @return Item
	 */
	public Item findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Item item = itemDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return item;
	}
	
	
	/*
	 * Método que valida si un ITEM ya existe por hotel
	 * @return Boolean
	 */
	private Boolean nameValidate(String hotelId, String description, Boolean update) throws MongoException, Exception {
		
		Item item = itemDao.findByHotelIdAndDescription(hotelId, description);
		
		if (item == null) {
			return false;
		}else if(update && item.getName().equals(description)) {
			return false;
		}else {
			return true;
		}
		
	}

}
