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

import co.com.hotelyando.core.business.ReservationBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Reservation;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Reservation")
public class ReservationController {
	
	private final ReservationBusiness reservationBusiness;
	
	private Generic<Reservation> generic;
	private Utilities utilities;
	private User user;
	
	public ReservationController(ReservationBusiness reservationBusiness) {
		this.reservationBusiness = reservationBusiness;
		
		utilities = new Utilities();
		generic = new Generic<Reservation>();
	}
	
	@PostMapping("/reservation")
	public ResponseEntity<ServiceResponse<Reservation>> save(@RequestBody Reservation reservation, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponse<Reservation> serviceResponse = reservationBusiness.save(reservation, user);
		ResponseEntity<ServiceResponse<Reservation>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
		
	}
	
	@PutMapping("/reservation")
	public ResponseEntity<ServiceResponse<Reservation>> update(@RequestBody Reservation reservation, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponse<Reservation> serviceResponse = reservationBusiness.update(reservation, user);
		ResponseEntity<ServiceResponse<Reservation>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	@GetMapping("/reservation/{reservationId}")
	public ResponseEntity<ServiceResponse<Reservation>> findByHotelIdAndUuid(@PathVariable String reservaId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponse<Reservation> serviceResponse = reservationBusiness.findByHotelIdAndUuid(user, reservaId);
		ResponseEntity<ServiceResponse<Reservation>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
			
	}
	
	@GetMapping("/reservation")
	public ResponseEntity<ServiceResponses<Reservation>> findByHotelId(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponses<Reservation> serviceResponses = reservationBusiness.findByHotelId(user);
		ResponseEntity<ServiceResponses<Reservation>> responseEntity = generic.returnResponseController(serviceResponses);
		
		return responseEntity;
		
	}

}
