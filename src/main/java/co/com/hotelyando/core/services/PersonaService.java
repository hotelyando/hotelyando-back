package co.com.hotelyando.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.hotelyando.database.dao.IPersonaDao;
import co.com.hotelyando.database.model.Persona;

@Service
@Transactional
public class PersonaService implements IPersonaService {

	@Autowired
	private IPersonaDao iPersonaDao;
	
	@Override
	public Persona consultarTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento) {
		
		Persona persona = null;
		
		try {
			
			persona = iPersonaDao.consultarTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return persona;
	}

	@Override
	public String registrarPersona(Persona persona) {
		
		String retorno = "";
		
		try {
			
			retorno = iPersonaDao.registrarPersona(persona);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	@Override
	public Persona consultarNumeroDocumento(String numeroDocumento) {
		
		Persona persona = null;
		
		try {
			
			persona = iPersonaDao.consultarNumeroDocumento(numeroDocumento);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return persona;
	}

}
