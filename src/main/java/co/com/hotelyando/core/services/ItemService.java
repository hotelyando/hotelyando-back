package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.database.dao.ItemDao;
import co.com.hotelyando.database.model.Item;

@Service
public class ItemService {
	
	private final ItemDao itemDao;
	
	public ItemService(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public String save(Item item) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(item.getUuid())) {
			messageReturn = PrintEntity.ITEM_ID;
		}else if(StringUtils.isBlank(item.getHotelId())) {
			messageReturn = PrintEntity.ITEM_HOTEL_ID;
		}else if(StringUtils.isBlank(item.getDescription())) {
			messageReturn = PrintEntity.ITEM_DESCRIPTION;
		}else if(item.getActive() == null) {
			messageReturn = PrintEntity.ITEM_ACTIVE;
		}else if(item.getPrice() == null) {
			messageReturn = PrintEntity.ITEM_PRICE;
		}else if(item.getQuantity() == null) {
			messageReturn = PrintEntity.ITEM_QUANTITY;
		}else if(item.getStock() == null) {
			messageReturn = PrintEntity.ITEM_STOCK;
		}else {
			itemDao.save(item);
		}
		
		//Hay item de Hotel e Item de habitación?, si es así, se debería colocar un tipo de item?
		
		return messageReturn;
	}
	
	public String update(Item item) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(item.getUuid())) {
			messageReturn = PrintEntity.ITEM_ID;
		}else if(StringUtils.isBlank(item.getHotelId())) {
			messageReturn = PrintEntity.ITEM_HOTEL_ID;
		}else if(StringUtils.isBlank(item.getDescription())) {
			messageReturn = PrintEntity.ITEM_DESCRIPTION;
		}else if(item.getActive() == null) {
			messageReturn = PrintEntity.ITEM_ACTIVE;
		}else if(item.getPrice() == null) {
			messageReturn = PrintEntity.ITEM_PRICE;
		}else if(item.getQuantity() == null) {
			messageReturn = PrintEntity.ITEM_QUANTITY;
		}else if(item.getStock() == null) {
			messageReturn = PrintEntity.ITEM_STOCK;
		}else {
			itemDao.update(item);
		}
		
		//Hay item de Hotel e Item de habitación?, si es así, se debería colocar un tipo de item?
		
		return messageReturn;
	}

	public List<Item> findByHotelId(String hotelId) throws Exception {
		
		List<Item> items = null;
		items = itemDao.findByHotelId(hotelId);
			
		return items;
	}

	public Item findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Item item = null;
		item = itemDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return item;
	}

}
