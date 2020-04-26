package co.com.hotelyando.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.repository.IPersonRepository;

@Repository
@Transactional
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

	
	public List<Person> findByDocumentTypeAndDocument(String documentType, String document) throws MongoException, Exception {
		
		Person person = null;
		List<Person> persons = new ArrayList<Person>();
		
		if(documentType.equals("")) {
			persons = iPersonRepository.findByDocumentLike(document);
		}else {
			persons = iPersonRepository.findByDocumentLike(document);
			
		}
		
		return persons;
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
	
	
	public void delete(String uuid) throws MongoException, Exception {
		
		iPersonRepository.deleteById(uuid);
	}

}
