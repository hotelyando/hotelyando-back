package co.com.hotelyando.core.services;

import co.com.hotelyando.database.model.Persona;

public interface IPersonaService {
	
	Persona consultarTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento);
	String registrarPersona(Persona persona);
	Persona consultarNumeroDocumento(String numeroDocumento);
}
