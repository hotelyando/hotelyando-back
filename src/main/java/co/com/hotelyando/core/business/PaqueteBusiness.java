package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PaqueteService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.database.model.Paquete;
import co.com.hotelyando.database.model.Usuario;

@Service
public class PaqueteBusiness {

private final PaqueteService paqueteService;
	
	private Genericos<Usuario> genericos;
	private Usuario objetoUsuario;
	
	public PaqueteBusiness(PaqueteService paqueteService) {
		this.paqueteService = paqueteService;
		
		genericos = new Genericos<>();
		
	}
	
	public String registrarPaquete(Paquete paquete, String usuario) {
		
		String retornoMensaje = "";
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			paquete.setHotelId(objetoUsuario.getHotelId());
			
			retornoMensaje = paqueteService.registrarPaquete(paquete);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Paquete> consultarPaquetesPorHotel(String usuario) {
		
		List<Paquete> paquetes = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			paquetes = paqueteService.consultarPaquetesPorHotel(objetoUsuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return paquetes;
	}

	public Paquete consultarPaquetePorHotel(String usuario, Integer paqueteId) {
		
		Paquete paquete = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			paquete = paqueteService.consultarPaquetePorHotel(objetoUsuario.getHotelId(), paqueteId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return paquete;
	}


}
