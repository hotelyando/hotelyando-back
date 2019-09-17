package co.com.hotelyando.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Persona;
import co.com.hotelyando.database.repository.IPersonaRepository;

@Repository
public class PersonaDao implements IPersonaDao{

	@Autowired
	private IPersonaRepository iPersonaRepository;
	
	@Override
	public Persona consultarTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento) throws Exception {
		
		Persona persona = null;
		
		persona = iPersonaRepository.findByTipoDocumentoTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
		
		return persona;
	}

	@Override
	public String registrarPersona(Persona persona) throws Exception {
		
		iPersonaRepository.save(persona);
		
		return null;
	}

	@Override
	public Persona consultarNumeroDocumento(String numeroDocumento) throws Exception {
		
		Persona persona = null;
		
		persona = iPersonaRepository.findByNumeroDocumento(numeroDocumento);
		
		return persona;
		
	}
	
	

}
