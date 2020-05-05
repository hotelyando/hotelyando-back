package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.ItemBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Item")
public class ItemController {
	
	private final ItemBusiness itemBusiness;
	
	private Utilities utilities;
	private User user;
	private Generic<Item> generic = null;
	
	public ItemController(ItemBusiness itemBusiness) {
		this.itemBusiness = itemBusiness;
		
		utilities = new Utilities();
		generic = new Generic<Item>();
	}
	
	
	@PostMapping("/item")
	public ResponseEntity<ServiceResponse<Item>> save(@RequestBody Item item, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Item> serviceResponse = itemBusiness.save(item, user); 
		ResponseEntity<ServiceResponse<Item>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	@PutMapping("/item")
	public ResponseEntity<ServiceResponse<Item>> update(@RequestParam(defaultValue = "") String itemId, @RequestParam(defaultValue = "") String saleId, @RequestBody Item item, @RequestHeader Map<String, String> headers){
		
		ServiceResponse<Item> serviceResponse = null;
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		
		if(itemId.equals("") && saleId.equals("")) {
			serviceResponse = itemBusiness.update(item, user); 
		}
		
		ResponseEntity<ServiceResponse<Item>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	
	@DeleteMapping("/item/{uuid}")
	public ResponseEntity<ServiceResponse<Item>> delete(@PathVariable String uuid, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Item> serviceResponse = itemBusiness.delete(uuid, user); 
		ResponseEntity<ServiceResponse<Item>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	
	@GetMapping("/item/{itemId}")
	public ResponseEntity<ServiceResponse<Item>> findByHotelIdAndUuid(@PathVariable String itemId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponse<Item> serviceResponse = itemBusiness.findByHotelIdAndUuid(user, itemId); 
		ResponseEntity<ServiceResponse<Item>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	@GetMapping("/item")
	public ResponseEntity<ServiceResponses<Item>> consultarItemsPorHotel(@RequestParam(defaultValue = "") String description, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponses<Item> serviceResponses = itemBusiness.findByHotelId(user, description); 
		ResponseEntity<ServiceResponses<Item>> responseEntity = generic.returnResponseController(serviceResponses);
		
		return responseEntity;
		
	}

}
