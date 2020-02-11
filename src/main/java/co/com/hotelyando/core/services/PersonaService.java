package co.com.hotelyando.core.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.ImpresionEntidades;
import co.com.hotelyando.core.utilities.ImpresionVariables;
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
		
		String retornoMensaje = "";
		
		if(StringUtils.isBlank(persona.getCelular())) {
			retornoMensaje = ImpresionEntidades.PERSONA_CELULAR;
		}else if(StringUtils.isBlank(persona.getCorreoElectronico())) {
			retornoMensaje = ImpresionEntidades.PERSONA_CORREO_ELECTRONICO;
		}else if(StringUtils.isBlank(persona.getDireccion())) {
			retornoMensaje = ImpresionEntidades.PERSONA_DIRECCION;
		}else if(StringUtils.isBlank(persona.getFechaNacimiento())) {
			retornoMensaje = ImpresionEntidades.PERSONA_FECHA_NACIMIENTO;
		}else if(StringUtils.isBlank(persona.getNombreCompleto())) {
			retornoMensaje = ImpresionEntidades.PERSONA_NOMBRE_COMPLETO;
		}else if(StringUtils.isBlank(persona.getNumeroDocumento())) {
			retornoMensaje = ImpresionEntidades.PERSONA_NUMERO_DOCUMENTO;
		}else if(StringUtils.isBlank(persona.getTelefonoFijo())) {
			retornoMensaje = ImpresionEntidades.PERSONA_TELEFONO_FIJO;
		}else if(StringUtils.isBlank(persona.getTipoDocumento().getTipoDocumento())) {
			retornoMensaje = ImpresionEntidades.PERSONA_TIPO_DOCUMENTO;
		}else {
			personaDao.registrarPersona(persona);
		}
		
		return retornoMensaje;
	}

	public Persona consultarNumeroDocumento(String numeroDocumento) throws Exception {
		
		Persona persona = null;
		persona = personaDao.consultarNumeroDocumento(numeroDocumento);
		
		return persona;
	}

}
