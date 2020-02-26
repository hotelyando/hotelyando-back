package co.com.hotelyando.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.CountryBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.database.model.Country;
import co.com.hotelyando.database.model.Hotel;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Country")
public class CountryController {

	private final CountryBusiness countryBusiness;
	
	public CountryController(CountryBusiness countryBusiness) {
		this.countryBusiness = countryBusiness;
	}
	
	@PostMapping("/country")
	public ResponseEntity<ServiceResponse<Country>> save(@RequestBody Country country){
		
		ServiceResponse<Country> serviceResponse = countryBusiness.save(country);
		
		return new ResponseEntity<ServiceResponse<Country>>(serviceResponse, HttpStatus.OK);
		
	}
}
