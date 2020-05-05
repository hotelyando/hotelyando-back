package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.EmployeeBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Employee;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Employee")
public class EmployeeController {

	private final EmployeeBusiness employeeBusiness;
	
	private Generic<Employee> generic = null;
	
	private Utilities utilities;
	
	private User user;
	
	public EmployeeController(EmployeeBusiness employeeBusiness) {
		this.employeeBusiness = employeeBusiness;
		
		generic = new Generic<Employee>();
		utilities = new Utilities();
	}
	
	
	@PostMapping("/employee")
	public ResponseEntity<ServiceResponse<Employee>> save(@RequestBody Employee employee, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Employee> serviceResponse = employeeBusiness.save(employee, user); 
		ResponseEntity<ServiceResponse<Employee>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	@PutMapping("/employee")
	public ResponseEntity<ServiceResponse<Employee>> update(@RequestBody Employee employee, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Employee> serviceResponse = employeeBusiness.update(employee, user); 
		ResponseEntity<ServiceResponse<Employee>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	
	@DeleteMapping("/employee/{uuid}")
	public ResponseEntity<ServiceResponse<Employee>> delete(@PathVariable String uuid, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		ServiceResponse<Employee> serviceResponse = employeeBusiness.delete(user, uuid); 
		ResponseEntity<ServiceResponse<Employee>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	@GetMapping("/employee")
	public ResponseEntity<ServiceResponses<Employee>> findByEmployee(@RequestParam(defaultValue = "") String uuid, @RequestParam(defaultValue = "") String personId, @RequestParam(defaultValue = "") String userId, @RequestHeader Map<String, String> headers){
		
		ServiceResponses<Employee> serviceResponses = null;
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		if(!personId.equals("")) {
			serviceResponses = employeeBusiness.findByPersonId(user, personId);
		}else if(!userId.equals("")){
			serviceResponses = employeeBusiness.findByUserId(user, userId);
		}else if(!uuid.equals("")) {
			serviceResponses = employeeBusiness.findByUuid(uuid);
		}else {
			serviceResponses = employeeBusiness.findByHotelId(user);
		}
		
		ResponseEntity<ServiceResponses<Employee>> responseEntity = generic.returnResponseController(serviceResponses);
		
		return responseEntity;
		
	}
}