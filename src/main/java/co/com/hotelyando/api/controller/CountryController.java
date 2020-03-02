package co.com.hotelyando.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.CountryBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.database.model.Country;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Country")
public class CountryController {

	private final CountryBusiness countryBusiness;
	private Generic<Country> generic = null;
	
	
	public CountryController(CountryBusiness countryBusiness) {
		this.countryBusiness = countryBusiness;
		
		generic = new Generic<Country>();
	}
	
	@PostMapping("/country")
	public ResponseEntity<ServiceResponse<Country>> save(@RequestBody Country country){
		
		ServiceResponse<Country> serviceResponse = countryBusiness.save(country);
		ResponseEntity<ServiceResponse<Country>> responseEntity = generic.returnResponseController(serviceResponse); 
		
		return responseEntity; 
		
	}
	
	@PutMapping("/country")
	public ResponseEntity<ServiceResponse<Country>> update(@RequestBody Country country){
		
		ServiceResponse<Country> serviceResponse = countryBusiness.update(country);
		ResponseEntity<ServiceResponse<Country>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
}
