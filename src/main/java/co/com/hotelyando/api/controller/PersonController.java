package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.PersonBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
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
	
	public PersonController(PersonBusiness personBusiness) {
		this.personBusiness = personBusiness;
		
		utilities = new Utilities();
	}
	
	@PostMapping("/person")
	public ResponseEntity<ServiceResponse<Person>> save(@RequestBody Person person, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.save(person, user);
		
		if(serviceResponse.getState().equals(PrintVariables.NEGOCIO)) {
			return new ResponseEntity<ServiceResponse<Person>>(serviceResponse, HttpStatus.OK);
		}else if(serviceResponse.getState().equals(PrintVariables.ERROR_TECNICO)){
			return new ResponseEntity<ServiceResponse<Person>>(serviceResponse, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<ServiceResponse<Person>>(serviceResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping("/person/{typeDocument}/{documentNumber}")
	public ResponseEntity<ServiceResponse<Person>> findByDocumentTypeAndDocument(@PathVariable String typeDocument, @PathVariable String documentNumber, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.findByDocumentTypeAndDocument(typeDocument, documentNumber, user);
		
		if(serviceResponse.getState().equals(PrintVariables.NEGOCIO)) {
			return new ResponseEntity<ServiceResponse<Person>>(serviceResponse, HttpStatus.OK);
		}else if(serviceResponse.getState().equals(PrintVariables.ERROR_TECNICO)){
			return new ResponseEntity<ServiceResponse<Person>>(serviceResponse, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<ServiceResponse<Person>>(serviceResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping("/person/{documentNumber}")
	public ResponseEntity<ServiceResponse<Person>> findByDocument(@PathVariable String documentNumber, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponse<Person> serviceResponse = personBusiness.findByDocument(documentNumber, user);
		
		if(serviceResponse.getState().equals(PrintVariables.NEGOCIO)) {
			return new ResponseEntity<ServiceResponse<Person>>(serviceResponse, HttpStatus.OK);
		}else if(serviceResponse.getState().equals(PrintVariables.ERROR_TECNICO)){
			return new ResponseEntity<ServiceResponse<Person>>(serviceResponse, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<ServiceResponse<Person>>(serviceResponse, HttpStatus.OK);
		}
	}
	
	
}
