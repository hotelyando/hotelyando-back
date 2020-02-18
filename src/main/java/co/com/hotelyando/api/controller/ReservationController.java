package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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

import co.com.hotelyando.core.business.ReservationBusiness;
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
	
	private Utilities utilities;
	private User user;
	
	public ReservationController(ReservationBusiness reservationBusiness) {
		this.reservationBusiness = reservationBusiness;
		
		utilities = new Utilities();
	}
	
	@PostMapping("/reservation")
	public ResponseEntity<String> save(@RequestBody Reservation reservation, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		String messageReturn = reservationBusiness.save(reservation, user);
		
		if(StringUtils.isEmpty(messageReturn)) {
			return new ResponseEntity<String>(messageReturn, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(messageReturn, HttpStatus.OK);
		}
	}
	
	@GetMapping("/reservation/{reservationId}")
	public ResponseEntity<Reservation> findByHotelIdAndUuid(@PathVariable String reservaId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		Reservation	reservation = reservationBusiness.findByHotelIdAndUuid(user, reservaId);
			
		if(reservation == null){
			return new ResponseEntity<Reservation>(reservation, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		}	
	}
	
	@GetMapping("/reservation")
	public ResponseEntity<List<Reservation>> findByHotelId(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		List<Reservation> reservations = reservationBusiness.findByHotelId(user);
		
		if(reservations == null){
			return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
		}
	}

}
