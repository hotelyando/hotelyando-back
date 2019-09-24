package co.com.hotelyando.core.services;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.PaisDao;
import co.com.hotelyando.database.model.Pais;

@Service
public class PaisService {
	
	private final PaisDao paisDao;
	
	public PaisService(PaisDao paisDao) {
		this.paisDao = paisDao;
	}
	
	public String registrarPais(Pais pais) throws Exception {
		
		paisDao.registrarPais(pais);
		
		return "";
		
	}
	

}
