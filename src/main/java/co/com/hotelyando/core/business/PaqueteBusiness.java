package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PaqueteService;
import co.com.hotelyando.database.model.Paquete;
import co.com.hotelyando.database.model.Usuario;

@Service
public class PaqueteBusiness {

private final PaqueteService paqueteService;
	
	public PaqueteBusiness(PaqueteService paqueteService) {
		this.paqueteService = paqueteService;
	}
	
	public String registrarPaquete(Paquete paquete, Usuario usuario) {
		
		String retornoMensaje = "";
		
		try {
			
			paquete.setHotelId(usuario.getHotelId());
			
			retornoMensaje = paqueteService.registrarPaquete(paquete);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Paquete> consultarPaquetesPorHotel(Usuario usuario) {
		
		List<Paquete> paquetes = null;
		
		try {
			
			paquetes = paqueteService.consultarPaquetesPorHotel(usuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return paquetes;
	}

	public Paquete consultarPaquetePorHotel(Usuario usuario, Integer paqueteId) {
		
		Paquete paquete = null;
		
		try {
			
			paquete = paqueteService.consultarPaquetePorHotel(usuario.getHotelId(), paqueteId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return paquete;
	}


}
