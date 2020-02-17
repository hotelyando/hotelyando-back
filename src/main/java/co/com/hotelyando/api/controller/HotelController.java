package co.com.hotelyando.api.controller;

import org.apache.commons.lang3.StringUtils;
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
	public ResponseEntity<String> save(@RequestBody Hotel hotel){
		
		String serviceResponse = hotelBusiness.save(hotel); 
		
		if(StringUtils.isEmpty(serviceResponse)) {
			return new ResponseEntity<String>(serviceResponse, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(serviceResponse, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<Hotel> findByUuid(@PathVariable String hotelId){
	
		Hotel hotel = hotelBusiness.findByUuid(hotelId);
		
		if(hotel == null) {
			return new ResponseEntity<Hotel>(hotel, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
		}
	}
	

}
