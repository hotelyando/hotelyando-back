package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PersonaService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.database.model.Persona;
import co.com.hotelyando.database.model.Usuario;

@Service
public class PersonaBusiness {

	private final PersonaService personaService;
	
	private Genericos<Usuario> genericos;
	private Usuario objetoUsuario;
	
	public PersonaBusiness(PersonaService personaService) {
		this.personaService = personaService;
		
		genericos = new Genericos<>();
	}
	
	
	public Persona consultarTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento, String usuario) {
		
		Persona persona = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			persona = personaService.consultarTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return persona;
	}

	public String registrarPersona(Persona persona, String usuario) {
		
		String retorno = "";
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			retorno = personaService.registrarPersona(persona);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	public Persona consultarNumeroDocumento(String numeroDocumento, String usuario) {
		
		Persona persona = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			persona = personaService.consultarNumeroDocumento(numeroDocumento);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return persona;
	}

}
