package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Paquete;
import co.com.hotelyando.database.repository.IPaqueteRepository;

@Repository
public class PaqueteDao implements IPaqueteDao{
	
	@Autowired
	private IPaqueteRepository iPaqueteRepository;
	
	@Override
	public String registrarPaquete(Paquete paquete) throws Exception {
		
		iPaqueteRepository.save(paquete);
		
		return null;
	}

	@Override
	public List<Paquete> consultarPaquetesPorHotel(Integer hotelId) throws Exception {
		
		List<Paquete> paquetes = null;
		
		paquetes = iPaqueteRepository.findByHotelId(hotelId);
		
		return paquetes;
	}

	@Override
	public Paquete consultarPaquetePorHotel(Integer hotelId, Integer paqueteId) throws Exception {
		
		Paquete paquete = null;
		
		paquete = iPaqueteRepository.findByHotelIdAndPaqueteId(hotelId, paqueteId);
		
		return paquete;
	}

}
