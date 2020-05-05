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

import co.com.hotelyando.core.business.HotelBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Hotel")
public class HotelController {
	
	private final HotelBusiness hotelBusiness;
	private Generic<Hotel> generic = null;
	
	private Utilities utilities;
	private User user;
	
	public HotelController(HotelBusiness hotelBusiness) {
		this.hotelBusiness = hotelBusiness;
		
		generic = new Generic<Hotel>();
		utilities = new Utilities();
	}
	
	@PostMapping("/hotel")
	public ResponseEntity<ServiceResponse<Hotel>> save(@RequestBody Hotel hotel, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Hotel> serviceResponse = hotelBusiness.save(hotel); 
		ResponseEntity<ServiceResponse<Hotel>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	@PutMapping("/hotel")
	public ResponseEntity<ServiceResponse<Hotel>> update(@RequestBody Hotel hotel, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Hotel> serviceResponse = hotelBusiness.update(user, hotel); 
		ResponseEntity<ServiceResponse<Hotel>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	@GetMapping("/hotel")
	public ResponseEntity<ServiceResponse<Hotel>> findByUuid(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Hotel> serviceResponse = hotelBusiness.findByUuid(user);
		ResponseEntity<ServiceResponse<Hotel>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	

}
