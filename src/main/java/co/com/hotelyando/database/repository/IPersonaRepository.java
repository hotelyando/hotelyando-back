package co.com.hotelyando.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Persona;

public interface IPersonaRepository extends MongoRepository<Persona, String> {
	
	Persona findByTipoDocumentoTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
	Persona findByNumeroDocumento(String numeroDocumento);
	
	

}
