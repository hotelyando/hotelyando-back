package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ItemRequest;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.InvoiceService;
import co.com.hotelyando.core.services.ItemService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Invoice;
import co.com.hotelyando.database.model.Invoice.ItemInvoice;
import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.model.User;
import co.com.hotelyando.database.repository.IInvoiceRepository;

@Service
public class ItemBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private InvoiceService invoiceService;
	
	private final ItemService itemService;
	
	private ServiceResponse<Item> serviceResponse;
	private ServiceResponses<Item> serviceResponses;
	
	private Utilities utilities;
	private Generic<Item> generic;
	
	private String messageReturn;
	
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
		
		try {
			
			item.setUuid(utilities.generadorId());
			item.setHotelId(user.getHotelId());
			
			messageReturn = itemService.save(item);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(item, PrintVariable.NEGOCIO, messageSource.getMessage("item.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}
	
	
	/*
	 * Método para la actualización de ITEM'S por hotel
	 * @return ServiceResponse<Item>
	 */
	public ServiceResponse<Item> update(Item item, User user) {
		
		try {
			
			item.setHotelId(user.getHotelId());
			
			messageReturn = itemService.update(item);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(item, PrintVariable.NEGOCIO, messageSource.getMessage("item.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}

	
	/*
	 * Método que lista los ITEM'S de un hotel
	 * @return ServiceResponse<Item>
	 */
	public ServiceResponses<Item> findByHotelId(User user) {
		
		List<Item> items;
		
		try {
			
			items = itemService.findByHotelId(user.getHotelId());
			
			if(items != null) {
				serviceResponses = generic.messagesReturn(items, PrintVariable.NEGOCIO, messageSource.getMessage("item.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("item.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
			
		return serviceResponses;
	}

	
	/*
	 * Método que retorna un ITEM de un hotel
	 * @return ServiceResponse<Item>
	 */
	public ServiceResponse<Item> findByHotelIdAndUuid(User user, String uuid) {
		
		Item item;
		
		try {
			
			item = itemService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(item != null) {
				serviceResponse = generic.messageReturn(item, PrintVariable.NEGOCIO, messageSource.getMessage("item.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("item.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
		
		return serviceResponse;
	}
	
	
	/*
	 * Método que actualiza el stock de un item y registra en la venta del cliente
	 * @return ServiceResponse<Item>
	 */
	public ServiceResponse<ItemRequest> updateStock(ItemRequest itemRequest, User user) {
		
		ServiceResponse<ItemRequest> serviceResponseItem;
		
		
		try {
			
			
			Item item = itemRequest.getItem();
			
			//Restamos la cantidad de items seleccionado
			item.setStock(item.getStock() - itemRequest.getStockQuantity() );
			
			if(item.getStock() <= 2) {
				messageSource.getMessage("item.update_ok", null, LocaleContextHolder.getLocale());
			}else {
				messageSource.getMessage("item.update_ok", null, LocaleContextHolder.getLocale());
			}
			
			messageReturn = itemService.update(item);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(item, PrintVariable.NEGOCIO, messageSource.getMessage("item.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
			//Consultamos la venta actual
			Invoice invoice = invoiceService.findByDocumentAndDocumentType(user.getHotelId(), "PROCESO", itemRequest.getDocument(), itemRequest.getDocumentType());
			
			
			//Actualizamos la venta
			
			Double total = itemRequest.getItem().getPrice() * itemRequest.getStockQuantity();
			
			
			invoice.getItems().get(invoice.getItems().size() + 1).setQuantity(itemRequest.getStockQuantity());
			invoice.getItems().get(invoice.getItems().size() + 1).setUuid(itemRequest.getItem().getUuid());
			invoice.getItems().get(invoice.getItems().size() + 1).setValues(itemRequest.getItem().getPrice());
			invoice.getItems().get(invoice.getItems().size() + 1).setTotal(total);
			
			messageReturn = invoiceService.update(invoice);
			
			//registrar en la venta
			//Validar la cantidad de stock si esta en dos menos mostrar un mensaje de alerta
			
			/*item.setHotelId(user.getHotelId());
			
			messageReturn = itemService.update(item);
			
			*/
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return null;
	}
}
