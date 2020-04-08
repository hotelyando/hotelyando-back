package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.ParameterBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Parameter;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Parameter")
public class ParameterController {

	private final ParameterBusiness parameterBusiness;
	
	private Generic<Parameter> generic = null;
	
	private Utilities utilities;
	
	private User user;
	
	public ParameterController(ParameterBusiness parameterBusiness) {
		this.parameterBusiness = parameterBusiness;
		
		generic = new Generic<Parameter>();
		utilities = new Utilities();
	}
	
	
	@PostMapping("/parameter")
	public ResponseEntity<ServiceResponse<Parameter>> save(@RequestBody Parameter parameter){
		
		ServiceResponse<Parameter> serviceResponse = parameterBusiness.save(parameter); 
		ResponseEntity<ServiceResponse<Parameter>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	@PutMapping("/parameter")
	public ResponseEntity<ServiceResponse<Parameter>> update(@RequestBody Parameter parameter){
		
		ServiceResponse<Parameter> serviceResponse = parameterBusiness.update(parameter); 
		ResponseEntity<ServiceResponse<Parameter>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
				
	}
	
	@GetMapping("/parameter")
	public ResponseEntity<ServiceResponse<Parameter>> findByUuid(@RequestParam(defaultValue = "") String uuid, @RequestHeader Map<String, String> headers){
		
		ServiceResponse<Parameter> serviceResponse = null;
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
		
		
		if(uuid.equals("")) {
			serviceResponse = parameterBusiness.findByHotelId(user);
		}else {
			serviceResponse = parameterBusiness.findByUuid(uuid);
		}
		
		ResponseEntity<ServiceResponse<Parameter>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	
	
}
