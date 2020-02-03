package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PersonaService;
import co.com.hotelyando.database.model.Persona;
import co.com.hotelyando.database.model.Usuario;

@Service
public class PersonaBusiness {

	private final PersonaService personaService;
	
	public PersonaBusiness(PersonaService personaService) {
		this.personaService = personaService;
	}
	
	
	public Persona consultarTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento, Usuario usuario) {
		
		Persona persona = null;
		
		try {
			
			persona = personaService.consultarTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return persona;
	}

	public String registrarPersona(Persona persona, Usuario usuario) {
		
		String retorno = "";
		
		try {
			
			retorno = personaService.registrarPersona(persona);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	public Persona consultarNumeroDocumento(String numeroDocumento, Usuario usuario) {
		
		Persona persona = null;
		
		try {
			
			persona = personaService.consultarNumeroDocumento(numeroDocumento);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return persona;
	}

}
