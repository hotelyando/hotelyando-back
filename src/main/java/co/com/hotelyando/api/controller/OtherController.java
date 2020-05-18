package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.EmployeeBusiness;
import co.com.hotelyando.core.business.OtherBusiness;
import co.com.hotelyando.core.model.DataEmployee;
import co.com.hotelyando.core.model.LiquidateItem;
import co.com.hotelyando.core.model.LiquidateRoom;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Employee;
import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Other")
public class OtherController {
	
	private final OtherBusiness otherBusiness;
	
	private Utilities utilities;
	
	private User user;
	
	public OtherController(OtherBusiness otherBusiness) {
		this.otherBusiness = otherBusiness;
		
		utilities = new Utilities();
	}
	
	
	@PostMapping("/other/employee")
	public ResponseEntity<ServiceResponse<DataEmployee>> save(@RequestBody DataEmployee dataEmployee, @RequestHeader Map<String, String> headers){
		
		Generic<DataEmployee> generic = new Generic<DataEmployee>();
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<DataEmployee> serviceResponse = otherBusiness.save(dataEmployee, user);
		ResponseEntity<ServiceResponse<DataEmployee>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	
	@PostMapping("/other/room/liquidate")
	public ResponseEntity<ServiceResponse<LiquidateRoom>> liquidate(@RequestBody LiquidateRoom liquidateRoom, @RequestHeader Map<String, String> headers){
		
		Generic<LiquidateRoom> generic = new Generic<LiquidateRoom>();
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		ServiceResponse<LiquidateRoom> serviceResponse = otherBusiness.liquidateRoom(user, liquidateRoom);
		
		ResponseEntity<ServiceResponse<LiquidateRoom>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}

	
	@PostMapping("/other/item/liquidate")
	public ResponseEntity<ServiceResponse<LiquidateItem>> liquidate(@RequestBody LiquidateItem liquidateItem, @RequestHeader Map<String, String> headers){
		
		Generic<LiquidateItem> generic = new Generic<LiquidateItem>();
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		ServiceResponse<LiquidateItem> serviceResponse = otherBusiness.liquidateItem(user, liquidateItem);
		
		ResponseEntity<ServiceResponse<LiquidateItem>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}

}
