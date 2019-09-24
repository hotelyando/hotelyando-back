package co.com.hotelyando.database.dao;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Pais;
import co.com.hotelyando.database.repository.IPaisRepository;

@Repository
public class PaisDao {
	
	private final IPaisRepository iPaisRepository;
	
	public PaisDao(IPaisRepository iPaisRepository) {
		this.iPaisRepository = iPaisRepository;
	}
	
	public String registrarPais(Pais pais) throws Exception {
		
		iPaisRepository.save(pais);
		
		return "";
		
	}
	
	

}
