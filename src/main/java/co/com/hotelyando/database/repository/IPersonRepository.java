package co.com.hotelyando.database.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Person;

@JaversSpringDataAuditable
public interface IPersonRepository extends MongoRepository<Person, String> {
	
	//Person findByDocumentTypeDocumentTypeAndDocument(String typeDocument, String documentNumber);
	Person findByDocument(String documentNumber);
	

}
