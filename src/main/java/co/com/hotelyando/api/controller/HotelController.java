package co.com.hotelyando.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.services.IHotelService;
import co.com.hotelyando.database.model.Hotel;

@RestController
public class HotelController {
	
	@Autowired
	private IHotelService iHotelService;
	
	@PostMapping("/hotel")
	public ResponseEntity<String> registrarHotel(@RequestBody Hotel hotel){
		
		String retornoRespuesta = "";
		
		retornoRespuesta = iHotelService.registrarHotel(hotel); 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<Hotel> consultarHotel(@PathVariable Integer hotelId){
		
		Hotel hotel = null;
		
		hotel = iHotelService.consultarHotel(hotelId);
		
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	

}
