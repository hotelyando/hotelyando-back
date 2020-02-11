package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.services.PersonaService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.core.utilities.ImpresionEntidades;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Persona;
import co.com.hotelyando.database.model.Usuario;

@Service
public class PersonaBusiness {

	private final PersonaService personaService;
	private RespuestaServicio<Persona> respuestaServicio = null;
	private Genericos<Persona> genericos = null;
	
	public PersonaBusiness(PersonaService personaService) {
		this.personaService = personaService;
		
		respuestaServicio = new RespuestaServicio<Persona>();
		genericos = new Genericos<Persona>();
	}
	
	
	public RespuestaServicio<Persona> consultarTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento, Usuario usuario) {
		
		Persona persona = null;
		
		try {
			
			persona = personaService.consultarTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
			
			if(persona != null) {
				respuestaServicio = genericos.retornoMensaje(persona, ImpresionVariables.NEGOCIO, ImpresionEntidades.PERSONA_EXISTENTE);
			}else {
				respuestaServicio = genericos.retornoMensaje(persona, ImpresionVariables.ADVERTENCIA, ImpresionEntidades.PERSONA_NO_ENCONTRADA);
			}
			
			
		}catch (Exception e) {
			respuestaServicio = genericos.retornoMensaje(persona, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return respuestaServicio;
	}

	public RespuestaServicio<Persona> registrarPersona(Persona persona, Usuario usuario) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = personaService.registrarPersona(persona);
			
			if(retornoMensaje.equals("")) {
				respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.NEGOCIO, ImpresionEntidades.USUARIO_REGISTRADO);
			}else {
				respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.ADVERTENCIA, retornoMensaje);
			}
			
		}catch (Exception e) {
			respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return respuestaServicio;
	}

	public RespuestaServicio<Persona> consultarNumeroDocumento(String numeroDocumento, Usuario usuario) {
		
		Persona persona = null;
		
		try {
			
			persona = personaService.consultarNumeroDocumento(numeroDocumento);
			
			if(persona != null) {
				respuestaServicio = genericos.retornoMensaje(persona, ImpresionVariables.NEGOCIO, ImpresionEntidades.PERSONA_EXISTENTE);
			}else {
				respuestaServicio = genericos.retornoMensaje(persona, ImpresionVariables.ADVERTENCIA, ImpresionEntidades.PERSONA_NO_ENCONTRADA);
			}
			
		}catch (Exception e) {
			respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return respuestaServicio;
	}

}
