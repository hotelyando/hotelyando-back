package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.PersonBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
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
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.save(person, user); 
		ResponseEntity<ServiceResponse<Person>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
	
	@PutMapping("/person")
	public ResponseEntity<ServiceResponse<Person>> update(@RequestBody Person person, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.update(person, user);
		ResponseEntity<ServiceResponse<Person>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
	@GetMapping("/person/{typeDocument}/{documentNumber}")
	public ResponseEntity<ServiceResponse<Person>> findByDocumentTypeAndDocument(@PathVariable String typeDocument, @PathVariable String documentNumber, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.findByDocumentTypeAndDocument(typeDocument, documentNumber, user);
		ResponseEntity<ServiceResponse<Person>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
	
	@GetMapping("/person/{uuid}")
	public ResponseEntity<ServiceResponse<Person>> findByUuid(@PathVariable String uuid, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.findByUuid(uuid);
		ResponseEntity<ServiceResponse<Person>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
	
	@GetMapping("/person")
	public ResponseEntity<ServiceResponses<Person>> findAllAndHotel(@RequestParam(defaultValue = "") String type, @RequestParam(defaultValue = "") String nationality, @RequestParam(defaultValue = "") String initDate, @RequestParam(defaultValue = "") String endDate, @RequestHeader Map<String, String> headers){
		
		ResponseEntity<ServiceResponses<Person>> responseEntity = null;
		ServiceResponses<Person> serviceResponses = null;
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		if(type.equals("")) {
			serviceResponses = personBusiness.findAllAndHotelIdAndSale(nationality, initDate, endDate, user);
		}else {
			serviceResponses = personBusiness.findPersonType(type, user);
		}
		
		responseEntity = generic.returnResponseController(serviceResponses);
		
		return responseEntity;
	}
	
	
	@DeleteMapping("/person/{uuid}")
	public ResponseEntity<ServiceResponse<Person>> delete(@PathVariable String uuid, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.delete(uuid, user); 
		ResponseEntity<ServiceResponse<Person>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
}
