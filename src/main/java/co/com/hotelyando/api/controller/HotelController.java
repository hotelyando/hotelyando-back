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

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class HotelController {
	
	private final HotelBusiness hotelBusiness;
	
	public HotelController(HotelBusiness hotelBusiness) {
		this.hotelBusiness = hotelBusiness;
	}
	
	@PostMapping("/hotel")
	public ResponseEntity<String> registrarHotel(@RequestBody Hotel hotel){
		
		String retornoRespuesta = hotelBusiness.registrarHotel(hotel); 
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<Hotel> consultarHotel(@PathVariable Integer hotelId){
	
		Hotel hotel = hotelBusiness.consultarHotel(hotelId);
		
		if(hotel == null) {
			return new ResponseEntity<Hotel>(hotel, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
		}
	}
	

}
