package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.PaqueteDao;
import co.com.hotelyando.database.model.Paquete;

@Service
public class PaqueteService {

	private PaqueteDao paqueteDao;
	
	public PaqueteService(PaqueteDao paqueteDao) {
		this.paqueteDao = paqueteDao;
	}
	
	
	public String registrarPaquete(Paquete paquete) throws Exception {
		
		String retornoMensaje = "";
			
		retornoMensaje = paqueteDao.registrarPaquete(paquete);
		
		return retornoMensaje;
	}

	public List<Paquete> consultarPaquetesPorHotel(String hotelId) throws Exception {
		
		List<Paquete> paquetes = null;
		paquetes = paqueteDao.consultarPaquetesPorHotel(hotelId);
		
		return paquetes;
	}

	public Paquete consultarPaquetePorHotel(String hotelId, String paqueteId) throws Exception {
		
		Paquete paquete = null;
		paquete = paqueteDao.consultarPaquetePorHotel(hotelId, paqueteId);
		
		return paquete;
	}

}
