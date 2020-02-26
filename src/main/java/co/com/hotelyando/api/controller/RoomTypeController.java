package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.RoomTypeBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.RoomType;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "RoomType")
public class RoomTypeController {
	
	private final RoomTypeBusiness roomTypeBusiness;
	
	private Utilities utilities;
	private User user;
	
	public RoomTypeController(RoomTypeBusiness roomTypeBusiness) {
		this.roomTypeBusiness = roomTypeBusiness;
		
		utilities = new Utilities();
	}
	
	@PostMapping("/roomtype")
	public ResponseEntity<ServiceResponse<RoomType>> save(@RequestBody RoomType roomType, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		ServiceResponse<RoomType> serviceResponse = roomTypeBusiness.save(roomType, user);
		
		return new ResponseEntity<ServiceResponse<RoomType>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@PutMapping("/roomtype")
	public ResponseEntity<ServiceResponse<RoomType>> update(@RequestBody RoomType roomType, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		ServiceResponse<RoomType> serviceResponse = roomTypeBusiness.update(roomType, user);
		
		return new ResponseEntity<ServiceResponse<RoomType>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("/roomtype")
	public ResponseEntity<ServiceResponses<RoomType>> findByHotelId(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		ServiceResponses<RoomType> serviceResponses = roomTypeBusiness.findByHotelId(user);
			
		return new ResponseEntity<ServiceResponses<RoomType>>(serviceResponses, HttpStatus.OK);
		
	}


}
