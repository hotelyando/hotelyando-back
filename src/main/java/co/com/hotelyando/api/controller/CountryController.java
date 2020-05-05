package co.com.hotelyando.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.CountryBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Country;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Country")
public class CountryController {

	private final CountryBusiness countryBusiness;
	private Generic<Country> generic = null;
	
	private Utilities utilities;
	
	public CountryController(CountryBusiness countryBusiness) {
		this.countryBusiness = countryBusiness;
		
		generic = new Generic<Country>();
		utilities = new Utilities();
	}
	
	
	/*
	 * Metodo que
	 * @retunr ResponseEntity
	 */
	@PostMapping("/country")
	public ResponseEntity<ServiceResponse<Country>> save(@RequestBody final Country country){
		
		final ServiceResponse<Country> serviceResponse = countryBusiness.save(country);
		final ResponseEntity<ServiceResponse<Country>> responseEntity = generic.returnResponseController(serviceResponse); 
		
		return responseEntity; 
		
	}
	
	/*
	 * 
	 */
	@PutMapping("/country")
	public ResponseEntity<ServiceResponse<Country>> update(@RequestBody Country country){
		
		ServiceResponse<Country> serviceResponse = countryBusiness.update(country);
		ResponseEntity<ServiceResponse<Country>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	@DeleteMapping("/country/{uuid}")
	public ResponseEntity<ServiceResponse<Country>> delete(@PathVariable String uuid){
		
		ServiceResponse<Country> serviceResponse = countryBusiness.delete(uuid);
		ResponseEntity<ServiceResponse<Country>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
	
	
	@GetMapping("/country")
	public ResponseEntity<ServiceResponses<Country>> findAll(){
		
		ServiceResponses<Country> serviceResponses = countryBusiness.findAll();
		ResponseEntity<ServiceResponses<Country>> responseEntity = generic.returnResponseController(serviceResponses);
		
		return responseEntity;
		
	}
	
	
	@GetMapping("/country/{nameCountry}")
	public ResponseEntity<ServiceResponse<Country>> findAll(@PathVariable String nameCountry){
		
		ServiceResponse<Country> serviceResponse = countryBusiness.findByNombre(nameCountry);
		ResponseEntity<ServiceResponse<Country>> responseEntity = generic.returnResponseController(serviceResponse);
		
		return responseEntity;
		
	}
}
