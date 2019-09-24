package co.com.hotelyando.core.services;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.PersonaDao;
import co.com.hotelyando.database.model.Persona;

@Service
public class PersonaService {

	private final PersonaDao personaDao;
	
	
	public PersonaService(PersonaDao personaDao) {
		this.personaDao = personaDao;
	}
	
	
	public Persona consultarTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento) throws Exception {
		
		Persona persona = null;
		persona = personaDao.consultarTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
		
		return persona;
	}

	public String registrarPersona(Persona persona) throws Exception {
		
		String retorno = "";
		
		retorno = personaDao.registrarPersona(persona);
		
		return retorno;
	}

	public Persona consultarNumeroDocumento(String numeroDocumento) throws Exception {
		
		Persona persona = null;
		persona = personaDao.consultarNumeroDocumento(numeroDocumento);
		
		return persona;
	}

}
