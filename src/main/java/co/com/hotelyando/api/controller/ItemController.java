package co.com.hotelyando.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.services.IItemService;
import co.com.hotelyando.database.model.Item;

@RestController
public class ItemController {
	
	@Autowired
	private IItemService iItemService;
	
	@PostMapping("/item")
	public ResponseEntity<String> registrarItem(@RequestBody Item item){
		
		String retornoRespuesta = "";
		
		retornoRespuesta = iItemService.registrarItem(item); 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/item/{hotelId}/{itemId}")
	public ResponseEntity<Item> consultarItemPorHotel(@PathVariable Integer hotelId, Integer itemId){
		
		Item item = null;
		
		item = iItemService.consultarItemPorHotel(hotelId, itemId);
		
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
	@GetMapping("/item/{hotelId}")
	public ResponseEntity<List<Item>> consultarItemsPorHotel(@PathVariable Integer hotelId){
		
		List<Item> items = null;
		
		items = iItemService.consultarItemsPorHotel(hotelId);
		
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}


}
