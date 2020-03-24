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

import co.com.hotelyando.core.business.UserBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "User")
public class UserController {
	
	private final UserBusiness userBusiness;
	
	private Utilities utilities;
	private User userJson;
	private Generic<User> generic = null;
	
	public UserController(UserBusiness userBusiness) {
		this.userBusiness = userBusiness;
		
		utilities = new Utilities();
		generic = new Generic<User>();
	}
	
	@PostMapping("/user")
	public ResponseEntity<ServiceResponse<User>> save(@RequestBody User user, @RequestHeader Map<String, String> headers){
		
		userJson = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponse<User> serviceResponse = userBusiness.save(user, userJson);
		ResponseEntity<ServiceResponse<User>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
	@PutMapping("/user")
	public ResponseEntity<ServiceResponse<User>> update(@RequestBody User user, @RequestHeader Map<String, String> headers){
		
		userJson = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponse<User> serviceResponse = userBusiness.update(user, userJson);
		ResponseEntity<ServiceResponse<User>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<ServiceResponse<User>> findByHotelIdAndUuid(@PathVariable String userId, @RequestHeader Map<String, String> headers){
		
		userJson = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponse<User> serviceResponse = userBusiness.findByHotelIdAndUuid(userJson, userId);
		ResponseEntity<ServiceResponse<User>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
	
	@GetMapping("/user")
	public ResponseEntity<ServiceResponses<User>> findByHotelId(@RequestHeader Map<String, String> headers){
		
		userJson = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponses<User> serviceResponse = userBusiness.findByHotelId(userJson);
		ResponseEntity<ServiceResponses<User>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
	}
}
