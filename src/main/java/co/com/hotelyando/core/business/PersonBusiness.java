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
import co.com.hotelyando.core.utilities.PrintVariable;
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
	
	private String messageReturn;
	
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
		
		try {
			
			person.setUuid(utilities.generadorId());
			
			messageReturn = personService.save(person);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(person, PrintVariable.NEGOCIO, messageSource.getMessage("person.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

	
	/*
	 * Método que actualiza una persona
	 * @return ServiceResponse<Person>
	 */
	public ServiceResponse<Person> update(Person person, User user) {
		
		try {
			
			messageReturn = personService.update(person);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(person, PrintVariable.NEGOCIO, messageSource.getMessage("person.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

	
	/*
	 * Método que retorna una persona por tipo y número de documento
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponse<Person> findByDocumentTypeAndDocument(String typeDocument, String documentNumber, User user) {
		
		try {
			
			Person person = personService.findByDocumentTypeAndDocument(typeDocument, documentNumber);
			
			if(person != null) {
				serviceResponse = generic.messageReturn(person, PrintVariable.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	/*
	 * Método que retorna una persona por tipo y número de documento
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponses<Person> findAll() {
		
		try {
			
			List<Person> persons = personService.findAll();
			
			if(persons != null) {
				serviceResponses = generic.messagesReturn(persons, PrintVariable.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}
	
	
	/*
	 * Método que retorna una persona dependiendo del tipo de persona empleado o huesper
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponses<Person> findAllAndHotelId(String personType, String initialDate, String finalDate, User user) {
		
		try {
			
			List<Person> persons = personService.findAll();
			
			if(persons != null) {
				serviceResponses = generic.messagesReturn(persons, PrintVariable.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}
	
	
	/*
	 * Método que retorna una persona dependiendo del tipo de persona empleado o huesper
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponses<Person> findPersonType(Integer employee, Integer guest, String document, User user) {
		
		try {
			
			List<Person> persons = personService.findByPerson(employee, guest, document);
			
			//Se tiene que buscar las personas por factura para saber el hotel
			
			if(persons != null) {
				serviceResponses = generic.messagesReturn(persons, PrintVariable.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}


}
