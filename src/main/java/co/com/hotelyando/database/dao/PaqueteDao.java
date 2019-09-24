package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Paquete;
import co.com.hotelyando.database.repository.IPaqueteRepository;

@Repository
public class PaqueteDao{
	
	private final IPaqueteRepository iPaqueteRepository;
	
	public PaqueteDao(IPaqueteRepository iPaqueteRepository) {
		this.iPaqueteRepository = iPaqueteRepository;
	}
	
	public String registrarPaquete(Paquete paquete) throws Exception {
		
		iPaqueteRepository.save(paquete);
		
		return null;
	}

	public List<Paquete> consultarPaquetesPorHotel(Integer hotelId) throws Exception {
		
		List<Paquete> paquetes = null;
		paquetes = iPaqueteRepository.findByHotelId(hotelId);
		
		return paquetes;
	}

	public Paquete consultarPaquetePorHotel(Integer hotelId, Integer paqueteId) throws Exception {
		
		Paquete paquete = null;
		paquete = iPaqueteRepository.findByHotelIdAndPaqueteId(hotelId, paqueteId);
		
		return paquete;
	}

}
