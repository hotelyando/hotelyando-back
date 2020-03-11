package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.PersonDao;
import co.com.hotelyando.database.model.Person;

@Service
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
		
		String messagesReturn = "";
		
		if(StringUtils.isBlank(person.getUuid())) {
			messagesReturn = messageSource.getMessage("person.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getCellPhone())) {
			messagesReturn = messageSource.getMessage("person.cell_phone", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getEmail())) {
			messagesReturn = messageSource.getMessage("person.email", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateEmail(person.getEmail())) {
			messagesReturn = messageSource.getMessage("person.email_format", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getAddress())) {
			messagesReturn = messageSource.getMessage("person.address", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getBirthdate())) {
			messagesReturn = messageSource.getMessage("person.birthdate", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getName())) {
			messagesReturn = messageSource.getMessage("person.full_name", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getDocument())) {
			messagesReturn = messageSource.getMessage("person.document_number", null, LocaleContextHolder.getLocale());
		}else if(validateDocument(person.getDocument())) {
			messagesReturn = messageSource.getMessage("person.document_number_unique", null, LocaleContextHolder.getLocale());
		}else if(person.getDocumentType() == null) {
			messagesReturn = messageSource.getMessage("person.type_document", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getPhone())) {
			messagesReturn = messageSource.getMessage("person.phone", null, LocaleContextHolder.getLocale());
		}else {
			personDao.save(person);
		}
		
		return messagesReturn;
	}
	
	
	/*
	 * Método para actualizar una persona
	 * @return String
	 */
	public String update(Person person) throws MongoException, Exception {
		
		String messagesReturn = "";
		
		if(StringUtils.isBlank(person.getUuid())) {
			messagesReturn = messageSource.getMessage("person.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getCellPhone())) {
			messagesReturn = messageSource.getMessage("person.cell_phone", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getEmail())) {
			messagesReturn = messageSource.getMessage("person.email", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateEmail(person.getEmail())) {
			messagesReturn = messageSource.getMessage("person.email_format", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getAddress())) {
			messagesReturn = messageSource.getMessage("person.address", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getBirthdate())) {
			messagesReturn = messageSource.getMessage("person.birthdate", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getName())) {
			messagesReturn = messageSource.getMessage("person.full_name", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getDocument())) {
			messagesReturn = messageSource.getMessage("person.document_number", null, LocaleContextHolder.getLocale());
		}else if(validateDocument(person.getDocument())) {
			messagesReturn = messageSource.getMessage("person.document_number_unique", null, LocaleContextHolder.getLocale());
		}else if(person.getDocumentType() == null) {
			messagesReturn = messageSource.getMessage("person.type_document", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(person.getPhone())) {
			messagesReturn = messageSource.getMessage("person.phone", null, LocaleContextHolder.getLocale());
		}else {
			personDao.update(person);
		}
		
		return messagesReturn;
	}

	
	/*
	 * Método que valida si un documento ya existe
	 * @return Boolean
	 */
	private Boolean validateDocument(String documentNumber) throws MongoException, Exception {
		
		Person person = null;
		person = personDao.findByDocument(documentNumber);
		
		if(person != null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/*
	 * Método que retorna el una persona por el tipo y número de documento
	 * @return Person
	 */
	public Person findByDocumentTypeAndDocument(String typeDocument, String documentNumber) throws MongoException, Exception {
		
		Person person = null;
		person = personDao.findByDocument(documentNumber);
		
		return person;
	}
	
	
	/*
	 * Método que retorna la lista de las personas registradas
	 * @return List<Person>
	 */
	public List<Person> findAll() throws MongoException, Exception {
		
		List<Person> persons = null;
		persons = personDao.findAll();
		
		return persons;
		
	}

}
