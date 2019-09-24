package co.com.hotelyando.core.business;

import java.util.List;

import co.com.hotelyando.core.services.ItemService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.model.Usuario;

public class ItemBusiness {
	
	private final ItemService itemService;
	
	private Genericos<Usuario> genericos;
	private Usuario objetoUsuario;
	
	public ItemBusiness(ItemService itemService) {
		this.itemService = itemService;
		
		genericos = new Genericos<>();
	}
	
	public String registrarItem(Item item, String usuario) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = itemService.registrarItem(item);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Item> consultarItemsPorHotel(String usuario) {
		
		List<Item> items = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			items = itemService.consultarItemsPorHotel(objetoUsuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return items;
	}

	public Item consultarItemPorHotel(String usuario, Integer itemId) {
		
		Item item = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			item = itemService.consultarItemPorHotel(objetoUsuario.getHotelId(), itemId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
}
