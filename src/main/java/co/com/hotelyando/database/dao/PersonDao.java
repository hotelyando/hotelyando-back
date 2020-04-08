package co.com.hotelyando.database.dao;

import java.util.List;

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
	
	public void save(Person person) throws MongoException, Exception {
		iPersonRepository.save(person);
	}
	
	public void update(Person person) throws MongoException, Exception {
		iPersonRepository.save(person);
	}

	
	public Person findByDocumentTypeAndDocument(String documentType, String document) throws MongoException, Exception {
		
		Person person = iPersonRepository.findByDocumentTypeAndDocument(documentType, document);
		
		return person;
	}
	
	
	public List<Person> findByDocument(String documentNumber) throws MongoException, Exception {
		
		List<Person> persons = iPersonRepository.findByDocument(documentNumber);
		
		return persons;
	}
	
	
	public List<Person> findAll() throws MongoException, Exception {
		
		List<Person> persons = iPersonRepository.findAll();
		
		return persons;
		
	}
	
	
	public Person findByUuid(String uuid) throws MongoException, Exception {
		
		Person person = iPersonRepository.findByUuid(uuid);
		
		return person;
		
	}

}
