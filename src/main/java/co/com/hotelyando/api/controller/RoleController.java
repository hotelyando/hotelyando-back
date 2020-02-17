package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.RoleBusiness;
import co.com.hotelyando.database.model.Role;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Role")
public class RoleController {
	
	private final RoleBusiness roleBusiness;
	
	public RoleController(RoleBusiness roleBusiness) {
		this.roleBusiness = roleBusiness;
	}
	
	@PostMapping("/role")
	public ResponseEntity<String> save(@RequestBody Role role, @RequestHeader Map<String, String> headers){
		
		String returnResponse = roleBusiness.save(role);
		
		if(StringUtils.isEmpty(returnResponse)) {
			return new ResponseEntity<String>(returnResponse, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(returnResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping("/role")
	public ResponseEntity<List<Role>> findAll(@RequestHeader Map<String, String> headers){
		
		List<Role> roles = roleBusiness.findAll();
		
		if(roles == null){
			return new ResponseEntity<List<Role>>(roles, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
		}
	}

}
