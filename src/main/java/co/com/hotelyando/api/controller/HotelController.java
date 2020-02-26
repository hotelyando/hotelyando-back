package co.com.hotelyando.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.HotelBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.database.model.Hotel;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Hotel")
public class HotelController {
	
	private final HotelBusiness hotelBusiness;
	
	public HotelController(HotelBusiness hotelBusiness) {
		this.hotelBusiness = hotelBusiness;
	}
	
	@PostMapping("/hotel")
	public ResponseEntity<ServiceResponse<Hotel>> save(@RequestBody Hotel hotel){
		
		ServiceResponse<Hotel> serviceResponse = hotelBusiness.save(hotel); 
		
		return new ResponseEntity<ServiceResponse<Hotel>>(serviceResponse, HttpStatus.OK);
				
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<ServiceResponse<Hotel>> findByUuid(@PathVariable String hotelId){
	
		ServiceResponse<Hotel> serviceResponse = hotelBusiness.findByUuid(hotelId);
		
		return new ResponseEntity<ServiceResponse<Hotel>>(serviceResponse, HttpStatus.OK);
		
	}
	

}
