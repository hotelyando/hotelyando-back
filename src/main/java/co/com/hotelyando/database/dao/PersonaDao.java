package co.com.hotelyando.database.dao;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Persona;
import co.com.hotelyando.database.repository.IPersonaRepository;

@Repository
public class PersonaDao{

	private final IPersonaRepository iPersonaRepository;
	
	public PersonaDao(IPersonaRepository iPersonaRepository) {
		this.iPersonaRepository = iPersonaRepository;
	}
	
	
	public Persona consultarTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento) throws Exception {
		
		Persona persona = null;
		persona = iPersonaRepository.findByTipoDocumentoTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
		
		return persona;
	}

	public String registrarPersona(Persona persona) throws Exception {
		
		iPersonaRepository.save(persona);
		
		return "Ok";
	}

	public Persona consultarNumeroDocumento(String numeroDocumento) throws Exception {
		
		Persona persona = null;
		persona = iPersonaRepository.findByNumeroDocumento(numeroDocumento);
		
		return persona;
		
	}

}
