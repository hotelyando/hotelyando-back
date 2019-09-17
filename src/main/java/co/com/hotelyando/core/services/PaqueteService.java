package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.IPaqueteDao;
import co.com.hotelyando.database.model.Paquete;

@Service
public class PaqueteService implements IPaqueteService {

	@Autowired
	private IPaqueteDao iPaqueteDao;
	
	@Override
	public String registrarPaquete(Paquete paquete) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = iPaqueteDao.registrarPaquete(paquete);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
	}

	@Override
	public List<Paquete> consultarPaquetesPorHotel(Integer hotelId) {
		
		List<Paquete> paquetes = null;
		
		try {
			
			paquetes = iPaqueteDao.consultarPaquetesPorHotel(hotelId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return paquetes;
	}

	@Override
	public Paquete consultarPaquetePorHotel(Integer hotelId, Integer paqueteId) {
		
		Paquete paquete = null;
		
		try {
			
			paquete = iPaqueteDao.consultarPaquetePorHotel(hotelId, paqueteId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return paquete;
	}

}
