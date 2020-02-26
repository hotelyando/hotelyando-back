package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import ch.qos.logback.classic.pattern.Util;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.services.PersonService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.User;

@Service
public class PersonBusiness {

	private final PersonService personService;
	private ServiceResponse<Person> serviceResponse = null;
	private Generic<Person> generic = null;
	private Utilities utilities = null;
	
	public PersonBusiness(PersonService personService) {
		this.personService = personService;
		
		serviceResponse = new ServiceResponse<Person>();
		generic = new Generic<Person>();
		utilities = new Utilities();
	}
	
	
	public ServiceResponse<Person> findByDocumentTypeAndDocument(String typeDocument, String documentNumber, User user) {
		
		Person person = null;
		
		try {
			
			person = personService.findByDocumentTypeAndDocument(typeDocument, documentNumber);
			
			if(person != null) {
				serviceResponse = generic.messageReturn(person, PrintVariables.NEGOCIO, PrintEntity.PERSONA_EXISTENTE);
			}else {
				serviceResponse = generic.messageReturn(person, PrintVariables.ADVERTENCIA, PrintEntity.PERSONA_NO_ENCONTRADA);
			}
			
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(person, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

	public ServiceResponse<Person> save(Person person, User user) {
		
		String retornoMensaje = "";
		
		try {
			
			person.setUuid(utilities.generadorId());
			
			retornoMensaje = personService.save(person);
			
			if(retornoMensaje.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, PrintEntity.USUARIO_REGISTRADO);
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, retornoMensaje);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

	public ServiceResponse<Person> findByDocument(String documentNumber, User user) {
		
		Person person = null;
		
		try {
			
			person = personService.findByDocument(documentNumber);
			
			if(person != null) {
				serviceResponse = generic.messageReturn(person, PrintVariables.NEGOCIO, PrintEntity.PERSONA_EXISTENTE);
			}else {
				serviceResponse = generic.messageReturn(person, PrintVariables.ADVERTENCIA, PrintEntity.PERSONA_NO_ENCONTRADA);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

}
