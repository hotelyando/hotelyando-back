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
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		String retornoRespuesta = itemBusiness.registrarItem(item, usuario);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/item/{itemId}")
	public ResponseEntity<Item> consultarItemPorHotel(@PathVariable Integer itemId, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		Item item = itemBusiness.consultarItemPorHotel(usuario, itemId);
			
		if(item == null) {
			return new ResponseEntity<Item>(item, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		}
	}
	
	@GetMapping("/item")
	public ResponseEntity<List<Item>> consultarItemsPorHotel(@RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		List<Item> items = itemBusiness.consultarItemsPorHotel(usuario);
			
		if(items == null) {
			return new ResponseEntity<List<Item>>(items, HttpStatus.NOT_IMPLEMENTED);
		}else {
			return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		}
	}

}
