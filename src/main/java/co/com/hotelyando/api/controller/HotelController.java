package co.com.hotelyando.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.HotelBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.database.model.Hotel;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Hotel")
public class HotelController {
	
	private final HotelBusiness hotelBusiness;
	private Generic<Hotel> generic = null;
	
	public HotelController(HotelBusiness hotelBusiness) {
		this.hotelBusiness = hotelBusiness;
		
		generic = new Generic<Hotel>();
	}
	
	@PostMapping("/hotel")
	public ResponseEntity<ServiceResponse<Hotel>> save(@RequestBody Hotel hotel){
		
		ServiceResponse<Hotel> serviceResponse = hotelBusiness.save(hotel); 
		ResponseEntity<ServiceResponse<Hotel>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	@PutMapping("/hotel")
	public ResponseEntity<ServiceResponse<Hotel>> update(@RequestBody Hotel hotel){
		
		ServiceResponse<Hotel> serviceResponse = hotelBusiness.update(hotel); 
		ResponseEntity<ServiceResponse<Hotel>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<ServiceResponse<Hotel>> findByUuid(@PathVariable String hotelId){
	
		ServiceResponse<Hotel> serviceResponse = hotelBusiness.findByUuid(hotelId);
		ResponseEntity<ServiceResponse<Hotel>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	

}
