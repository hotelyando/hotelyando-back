package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.UserBusiness;
import co.com.hotelyando.core.model.LoginRequest;
import co.com.hotelyando.core.model.LoginResponse;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.utilities.PrintVariable;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@Api(tags = "Login")
public class LoginController {
	
	
	private final UserBusiness userBusiness;
	private ServiceResponse<LoginResponse> serviceResponse = null;
	
	public LoginController(UserBusiness userBusiness) {
		this.userBusiness = userBusiness;
	}
	
	@PostMapping("/login")
	public ResponseEntity<ServiceResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
		
		serviceResponse = userBusiness.findByUserAndPassword(loginRequest.getUser(), loginRequest.getPassword());
			
		return new ResponseEntity<ServiceResponse<LoginResponse>>(serviceResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/login")
	public ResponseEntity<ServiceResponse<LoginResponse>> logout(@RequestHeader Map<String, String> headers){
		
		serviceResponse = userBusiness.logout(headers, PrintVariable.TOKEN_HEADER);
			
		return new ResponseEntity<ServiceResponse<LoginResponse>>(serviceResponse, HttpStatus.OK);
	}

}
