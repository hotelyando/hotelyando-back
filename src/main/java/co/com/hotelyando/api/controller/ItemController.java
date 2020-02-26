package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.ItemBusiness;
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
	public ResponseEntity<String> save(@RequestBody Item item, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		String messageReturn = itemBusiness.save(item, user);
		
		if(StringUtils.isEmpty(messageReturn)) {
			return new ResponseEntity<String>(messageReturn, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(messageReturn, HttpStatus.OK);
		}
	}
	
	@GetMapping("/item/{itemId}")
	public ResponseEntity<Item> findByHotelIdAndUuid(@PathVariable String itemId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		Item item = itemBusiness.findByHotelIdAndUuid(user, itemId);
			
		if(item == null) {
			return new ResponseEntity<Item>(item, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		}
	}
	
	@GetMapping("/item")
	public ResponseEntity<List<Item>> consultarItemsPorHotel(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		List<Item> items = itemBusiness.findByHotelId(user);
			
		if(items == null) {
			return new ResponseEntity<List<Item>>(items, HttpStatus.NOT_IMPLEMENTED);
		}else {
			return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		}
	}

}
