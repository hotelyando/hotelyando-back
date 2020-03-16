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

import co.com.hotelyando.core.business.RoleBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Role;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Role")
public class RoleController {
	
	private final RoleBusiness roleBusiness;
	
	private Generic<Role> generic = null;
	
	private Utilities utilities;
	private User user;
	
	public RoleController(RoleBusiness roleBusiness) {
		this.roleBusiness = roleBusiness;
		
		utilities = new Utilities();
		generic = new Generic<Role>();
	}
	
	@PostMapping("/role")
	public ResponseEntity<ServiceResponse<Role>> save(@RequestBody Role role, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponse<Role> serviceResponse = roleBusiness.save(role, user);
		
		ResponseEntity<ServiceResponse<Role>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	@PutMapping("/role")
	public ResponseEntity<ServiceResponse<Role>> update(@RequestBody Role role, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponse<Role> serviceResponse = roleBusiness.update(role, user);
		
		ResponseEntity<ServiceResponse<Role>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	@GetMapping("/role")
	public ResponseEntity<ServiceResponses<Role>> findAll(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponses<Role> serviceResponses = roleBusiness.findByHotelId(user);
		
		ResponseEntity<ServiceResponses<Role>> responseEntity = generic.returnResponseController(serviceResponses);
		
		return responseEntity;
		
	}
	
	@GetMapping("/role/{roleName}")
	public ResponseEntity<ServiceResponse<Role>> findAll(@PathVariable String roleName, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
		
		ServiceResponse<Role> serviceResponse = roleBusiness.findByName(user, roleName);
		
		ResponseEntity<ServiceResponse<Role>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}

}
