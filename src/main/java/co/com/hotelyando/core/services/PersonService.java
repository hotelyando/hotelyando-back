package co.com.hotelyando.core.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.PersonDao;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.model.User;

@Service
@Transactional
public class PersonService {

	@Autowired
	private MessageSource messageSource;
	
	private RegularExpression regularExpression = null;
	
	private final PersonDao personDao;
	
	public PersonService(PersonDao personDao) {
		this.personDao = personDao;
		
		regularExpression = new RegularExpression();
	}
	
	/*
	 * Método para registrar una persona 
	 * @return String
	 */
	public String save(Person person) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(person.getUuid())) {
			messageReturn = messageSource.getMessage("person.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getCellPhone())) {
			messageReturn = messageSource.getMessage("person.cell_phone", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateEmail(person.getEmail())) {
			messageReturn = messageSource.getMessage("person.email_format", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getAddress())) {
			messageReturn = messageSource.getMessage("person.address", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getBirthdate())) {
			messageReturn = messageSource.getMessage("person.birthdate", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getFirstName())) {
			messageReturn = messageSource.getMessage("person.first_name", null, LocaleContextHolder.getLocale());
		}else if(!person.getDocumentType().equals("NIT") && StringUtils.isBlank(person.getLastName())) {
			messageReturn = messageSource.getMessage("person.last_name", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getDocument())) {
			messageReturn = messageSource.getMessage("person.document_number", null, LocaleContextHolder.getLocale());
		}else if(validateDocument(person.getDocument())) {
			messageReturn = messageSource.getMessage("person.document_number_unique", null, LocaleContextHolder.getLocale());
		}else if(person.getDocumentType() == null) {
			messageReturn = messageSource.getMessage("person.type_document", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getPhone())) {
			messageReturn = messageSource.getMessage("person.phone", null, LocaleContextHolder.getLocale());
		}else {
			personDao.save(person);
		}
		
		return messageReturn;
	}
	
	
	/*
	 * Método para actualizar una persona
	 * @return String
	 */
	public String update(Person person) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(person.getUuid())) {
			messageReturn = messageSource.getMessage("person.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getCellPhone())) {
			messageReturn = messageSource.getMessage("person.cell_phone", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateEmail(person.getEmail())) {
			messageReturn = messageSource.getMessage("person.email_format", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getAddress())) {
			messageReturn = messageSource.getMessage("person.address", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getBirthdate())) {
			messageReturn = messageSource.getMessage("person.birthdate", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getFirstName())) {
			messageReturn = messageSource.getMessage("person.first_name", null, LocaleContextHolder.getLocale());
		}else if(!person.getDocumentType().equals("NIT") && StringUtils.isBlank(person.getLastName())) {
			messageReturn = messageSource.getMessage("person.last_name", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getDocument())) {
			messageReturn = messageSource.getMessage("person.document_number", null, LocaleContextHolder.getLocale());
		}else if(validateDocument(person.getDocument())) {
			messageReturn = messageSource.getMessage("person.document_number_unique", null, LocaleContextHolder.getLocale());
		}else if(person.getDocumentType() == null) {
			messageReturn = messageSource.getMessage("person.type_document", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getPhone())) {
			messageReturn = messageSource.getMessage("person.phone", null, LocaleContextHolder.getLocale());
		}else {
			personDao.update(person);
		}
		
		return messageReturn;
	}

	
	/*
	 * Método que valida si un documento ya existe
	 * @return Boolean
	 */
	private Boolean validateDocument(String documentNumber) throws MongoException, Exception {
		
		List<Person> persons = personDao.findByDocument(documentNumber);
		
		if(persons != null && persons.size() > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/*
	 * Método que retorna el una persona por el tipo y número de documento
	 * @return Person
	 */
	public Person findByDocumentTypeAndDocument(String documentType, String document) throws MongoException, Exception {
		
		Person person = personDao.findByDocumentTypeAndDocument(documentType, document);
		
		return person;
	}
	
	
	/*
	 * Método que retorna la lista de las personas registradas
	 * @return List<Person>
	 */
	public List<Person> findAll() throws MongoException, Exception {
		
		List<Person> persons = personDao.findAll();
		
		return persons;
		
	}
	
	
	public List<Person> findByPerson(String data, List<User> users) throws MongoException, Exception {
		
		List<Person> persons = new ArrayList<Person>();
		
		User user = null;
		Person person = null;
		
		for(int a = 0; a < users.size(); a++) {
			
			user = users.get(a);
			
			person = personDao.findByUuid(user.getPersonId());
			persons.add(person);
		}
		
		return persons;
		
	}

	
	
	public List<Person> findByPerson(List<Sale> sales, String data) throws MongoException, Exception {
		
		List<Person> persons = new ArrayList<Person>();
		
		Sale sale = null;
		Person person = null;
		
		for(int a = 0; a < sales.size(); a++) {
			
			sale = sales.get(a);
			
			person = personDao.findByUuid(sale.getClient().getUuid());
			persons.add(person);
		}
		
		return persons;
		
	}
	
	
	public Person findByUuid(String uuid) throws MongoException, Exception {
		
		Person person = personDao.findByUuid(uuid);
		
		return person;
	}
	
	
	public String delete(String uuid) throws MongoException, Exception {
		
		String messageReturn = "";
		
		personDao.delete(uuid);
		
		return messageReturn;
		
	}

}
