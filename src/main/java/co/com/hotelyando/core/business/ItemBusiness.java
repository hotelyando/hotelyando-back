package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.ItemService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.model.User;

@Service
public class ItemBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
	private final ItemService itemService;
	private Utilities utilities = null;
	private ServiceResponse<Item> serviceResponse;
	private ServiceResponses<Item> serviceResponses;
	private Generic<Item> generic = null;
	
	public ItemBusiness(ItemService itemService) {
		this.itemService = itemService;
		
		serviceResponse = new ServiceResponse<Item>();
		serviceResponses = new ServiceResponses<Item>();
		utilities = new Utilities();
		generic = new Generic<Item>();
	}
	
	
	/*
	 * Método para el registro de ITEM'S por hotel
	 * @return ServiceResponse<Item>
	 */
	public ServiceResponse<Item> save(Item item, User user) {
		
		String messageReturn = "";
		
		try {
			
			item.setUuid(utilities.generadorId());
			item.setHotelId(user.getHotelId());
			
			messageReturn = itemService.save(item);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(item, PrintVariables.NEGOCIO, messageSource.getMessage("item.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}
	
	
	/*
	 * Método para la actualización de ITEM'S por hotel
	 * @return ServiceResponse<Item>
	 */
	public ServiceResponse<Item> update(Item item, User user) {
		
		String messageReturn = "";
		
		try {
			
			item.setHotelId(user.getHotelId());
			
			messageReturn = itemService.update(item);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(item, PrintVariables.NEGOCIO, messageSource.getMessage("item.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}

	
	/*
	 * Método que lista los ITEM'S de un hotel
	 * @return ServiceResponse<Item>
	 */
	public ServiceResponses<Item> findByHotelId(User user) {
		
		List<Item> items = null;
		
		try {
			
			items = itemService.findByHotelId(user.getHotelId());
			
			if(items != null) {
				serviceResponses = generic.messagesReturn(items, PrintVariables.NEGOCIO, messageSource.getMessage("item.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariables.NEGOCIO, messageSource.getMessage("item.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
			
		return serviceResponses;
	}

	
	/*
	 * Método que retorna un ITEM de un hotel
	 * @return ServiceResponse<Item>
	 */
	public ServiceResponse<Item> findByHotelIdAndUuid(User user, String uuid) {
		
		Item item = null;
		
		try {
			
			item = itemService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(item != null) {
				serviceResponse = generic.messageReturn(item, PrintVariables.NEGOCIO, messageSource.getMessage("item.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, messageSource.getMessage("item.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
		
		return serviceResponse;
	}
}
