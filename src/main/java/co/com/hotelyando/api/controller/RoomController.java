package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
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

import co.com.hotelyando.core.business.RoomBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Room")
public class RoomController {
	
	private final RoomBusiness roomBusiness;
	
	private Utilities utilities;
	private User user;
	
	public RoomController(RoomBusiness roomBusiness) {
		this.roomBusiness = roomBusiness;
		
		utilities = new Utilities();
	}
	
	@PostMapping("/room")
	public ResponseEntity<ServiceResponse<Room>> save(@RequestBody Room room, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		ServiceResponse<Room> serviceResponse = roomBusiness.save(room, user);
		
		return new ResponseEntity<ServiceResponse<Room>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@PutMapping("/room")
	public ResponseEntity<ServiceResponse<Room>> update(@RequestBody Room room, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		ServiceResponse<Room> serviceResponse = roomBusiness.update(room, user);
		
		return new ResponseEntity<ServiceResponse<Room>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("/room/{roomId}")
	public ResponseEntity<ServiceResponse<Room>> findByHotelIdAndUuid(@PathVariable String roomId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		ServiceResponse<Room> serviceResponse = roomBusiness.findByHotelIdAndUuid(user, roomId);
			
		return new ResponseEntity<ServiceResponse<Room>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("/room")
	public ResponseEntity<ServiceResponses<Room>> findByHotelId(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		ServiceResponses<Room> serviceResponses = roomBusiness.findByHotelId(user);
			
		return new ResponseEntity<ServiceResponses<Room>>(serviceResponses, HttpStatus.OK);
		
	}


}
