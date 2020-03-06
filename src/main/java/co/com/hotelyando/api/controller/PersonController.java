package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.PersonBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Person")
public class PersonController {

	private final PersonBusiness personBusiness;
	
	private Utilities utilities;
	private User user;
	private Generic<Person> generic = null;
	
	public PersonController(PersonBusiness personBusiness) {
		this.personBusiness = personBusiness;
		
		utilities = new Utilities();
		generic = new Generic<Person>();
	}
	
	@PostMapping("/person")
	public ResponseEntity<ServiceResponse<Person>> save(@RequestBody Person person, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.save(person, user); 
		ResponseEntity<ServiceResponse<Person>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
	
	@PutMapping("/person")
	public ResponseEntity<ServiceResponse<Person>> update(@RequestBody Person person, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.update(person, user);
		ResponseEntity<ServiceResponse<Person>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
	@GetMapping("/person/{typeDocument}/{documentNumber}")
	public ResponseEntity<ServiceResponse<Person>> findByDocumentTypeAndDocument(@PathVariable String typeDocument, @PathVariable String documentNumber, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.findByDocumentTypeAndDocument(typeDocument, documentNumber, user);
		ResponseEntity<ServiceResponse<Person>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
}
