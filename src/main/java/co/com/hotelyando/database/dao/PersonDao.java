package co.com.hotelyando.database.dao;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.repository.IPersonRepository;

@Repository
public class PersonDao{

	private final IPersonRepository iPersonRepository;
	
	public PersonDao(IPersonRepository iPersonRepository) {
		this.iPersonRepository = iPersonRepository;
	}
	
	
	public Person findByDocumentTypeAndDocument(String typeDocument, String documentNumber) throws MongoException, Exception {
		
		Person person = null;
		//person = iPersonRepository.findByDocumentTypeDocumentTypeAndDocument(typeDocument, documentNumber);
		
		return person;
	}

	public void save(Person person) throws Exception {
		iPersonRepository.save(person);
	}
	
	public void update(Person person) throws Exception {
		iPersonRepository.save(person);
	}

	public Person findByDocument(String documentNumber) throws MongoException, Exception {
		
		Person person = null;
		person = iPersonRepository.findByDocument(documentNumber);
		
		return person;
	}

}
