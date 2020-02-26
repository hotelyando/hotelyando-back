package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.ItemBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.PrintVariables;
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
	
	public ItemController(ItemBusiness itemBusiness) {
		this.itemBusiness = itemBusiness;
		
		utilities = new Utilities();
	}
	
	
	@PostMapping("/item")
	public ResponseEntity<ServiceResponse<Item>> save(@RequestBody Item item, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponse<Item> serviceResponse = itemBusiness.save(item, user);
		
		return new ResponseEntity<ServiceResponse<Item>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@PutMapping("/item")
	public ResponseEntity<ServiceResponse<Item>> update(@RequestBody Item item, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponse<Item> serviceResponse = itemBusiness.update(item, user);
		
		return new ResponseEntity<ServiceResponse<Item>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("/item/{itemId}")
	public ResponseEntity<ServiceResponse<Item>> findByHotelIdAndUuid(@PathVariable String itemId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponse<Item> serviceResponse = itemBusiness.findByHotelIdAndUuid(user, itemId);
			
		return new ResponseEntity<ServiceResponse<Item>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("/item")
	public ResponseEntity<ServiceResponses<Item>> consultarItemsPorHotel(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponses<Item> serviceResponses = itemBusiness.findByHotelId(user);
			
		return new ResponseEntity<ServiceResponses<Item>>(serviceResponses, HttpStatus.OK);
		
	}

}
