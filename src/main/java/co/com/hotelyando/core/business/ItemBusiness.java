package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

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
	
	public ServiceResponse<Item> save(Item item, User user) {
		
		String messageReturn = "";
		
		try {
			
			item.setUuid(utilities.generadorId());
			item.setHotelId(user.getHotelId());
			
			messageReturn = itemService.save(item);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, "Registro exitoso!.");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, "Problemas registrando el item!.");
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}
	
	public ServiceResponse<Item> update(Item item, User user) {
		
		String messageReturn = "";
		
		try {
			
			item.setHotelId(user.getHotelId());
			
			messageReturn = itemService.update(item);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, "Registro exitoso!.");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, "Problemas registrando el item!.");
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}

	public ServiceResponses<Item> findByHotelId(User user) {
		
		List<Item> items = null;
		
		try {
			
			items = itemService.findByHotelId(user.getHotelId());
			
			if(items != null) {
				serviceResponses = generic.messagesReturn(items, PrintVariables.NEGOCIO, "Consulta");
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariables.ADVERTENCIA, "Problemas consultando los items!.");
			}
			
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}

	public ServiceResponse<Item> findByHotelIdAndUuid(User user, String uuid) {
		
		Item item = null;
		
		try {
			
			item = itemService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(item != null) {
				serviceResponse = generic.messageReturn(item, PrintVariables.NEGOCIO, "Consulta");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, "Problemas consultando los items!.");
			}
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
}
