package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.PersonService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.User;

@Service
public class PersonBusiness {

	@Autowired
	private MessageSource messageSource;
	
	private final PersonService personService;
	private ServiceResponse<Person> serviceResponse = null;
	private ServiceResponses<Person> serviceResponses = null;
	private Generic<Person> generic = null;
	private Utilities utilities = null;
	
	public PersonBusiness(PersonService personService) {
		this.personService = personService;
		
		serviceResponse = new ServiceResponse<Person>();
		serviceResponses = new ServiceResponses<Person>();
		generic = new Generic<Person>();
		utilities = new Utilities();
	}
	
	
	/*
	 * Método que registra una persona
	 * @return ServiceResponse<Person>
	 */
	public ServiceResponse<Person> save(Person person, User user) {
		
		String messageReturn = "";
		
		try {
			
			person.setUuid(utilities.generadorId());
			
			messageReturn = personService.save(person);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(person, PrintVariables.NEGOCIO, messageSource.getMessage("person.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

	
	/*
	 * Método que actualiza una persona
	 * @return ServiceResponse<Person>
	 */
	public ServiceResponse<Person> update(Person person, User user) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = personService.update(person);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(person, PrintVariables.NEGOCIO, messageSource.getMessage("person.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

	
	/*
	 * Método que retorna una persona por tipo y número de documento
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponse<Person> findByDocumentTypeAndDocument(String typeDocument, String documentNumber, User user) {
		
		Person person = null;
		
		try {
			
			person = personService.findByDocumentTypeAndDocument(typeDocument, documentNumber);
			
			if(person != null) {
				serviceResponse = generic.messageReturn(person, PrintVariables.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	/*
	 * Método que retorna una persona por tipo y número de documento
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponses<Person> findAll() {
		
		List<Person> persons = null;
		
		try {
			
			persons = personService.findAll();
			
			if(persons != null) {
				serviceResponses = generic.messagesReturn(persons, PrintVariables.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariables.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}


}
