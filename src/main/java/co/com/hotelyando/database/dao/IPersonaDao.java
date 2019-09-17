package co.com.hotelyando.database.dao;

import co.com.hotelyando.database.model.Persona;

public interface IPersonaDao {
	
	Persona consultarTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento) throws Exception;
	String registrarPersona(Persona persona) throws Exception;
	Persona consultarNumeroDocumento(String numeroDocumento) throws Exception;
	

}
