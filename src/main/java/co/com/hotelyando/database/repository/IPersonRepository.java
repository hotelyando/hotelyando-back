package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import co.com.hotelyando.database.model.Person;

@JaversSpringDataAuditable
@Transactional
public interface IPersonRepository extends MongoRepository<Person, String> {
	
	Person findByDocumentTypeAndDocument(String documentType, String document);
	List<Person> findByDocumentLike(String document);
	Person findByUuid(String uuid);
	List<Person> findByDocument(String documentNumber);
	
	

}
