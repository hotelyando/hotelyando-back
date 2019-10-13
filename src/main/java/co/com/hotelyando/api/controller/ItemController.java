package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

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
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Item;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ItemController {
	
private ItemBusiness itemBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public ItemController(ItemBusiness itemBusiness) {
		this.itemBusiness = itemBusiness;
	}
	
	public ItemController() {
		utilidades = new Utilidades();
	}
	
	@PostMapping("/item")
	public ResponseEntity<String> registrarItem(@RequestBody Item item, @RequestHeader Map<String, String> headers){
		
		String retornoRespuesta = "";
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			retornoRespuesta = itemBusiness.registrarItem(item, usuario);
		}catch (Exception e) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/item/{itemId}")
	public ResponseEntity<Item> consultarItemPorHotel(@PathVariable Integer itemId, @RequestHeader Map<String, String> headers){
		
		Item item = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			item = itemBusiness.consultarItemPorHotel(usuario, itemId);
			
		}catch (Exception e) {
			return new ResponseEntity<Item>(item, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<Item>(item, HttpStatus.OK);
		
	}
	
	@GetMapping("/item")
	public ResponseEntity<List<Item>> consultarItemsPorHotel(@RequestHeader Map<String, String> headers){
		
		List<Item> items = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			items = itemBusiness.consultarItemsPorHotel(usuario);
			
		}catch (Exception e) {
			return new ResponseEntity<List<Item>>(items, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		
		
	}

}
