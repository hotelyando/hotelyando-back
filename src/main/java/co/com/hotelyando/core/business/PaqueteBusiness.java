package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PaqueteService;
import co.com.hotelyando.database.model.Paquete;
import co.com.hotelyando.database.model.User;

@Service
public class PaqueteBusiness {

private final PaqueteService paqueteService;
	
	public PaqueteBusiness(PaqueteService paqueteService) {
		this.paqueteService = paqueteService;
	}
	
	public String registrarPaquete(Paquete paquete, User user) {
		
		String retornoMensaje = "";
		
		try {
			
			paquete.setHotelId(user.getHotelId());
			
			retornoMensaje = paqueteService.registrarPaquete(paquete);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Paquete> consultarPaquetesPorHotel(User user) {
		
		List<Paquete> paquetes = null;
		
		try {
			
			paquetes = paqueteService.consultarPaquetesPorHotel(user.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return paquetes;
	}

	public Paquete consultarPaquetePorHotel(User user, String paqueteId) {
		
		Paquete paquete = null;
		
		try {
			
			paquete = paqueteService.consultarPaquetePorHotel(user.getHotelId(), paqueteId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return paquete;
	}


}
