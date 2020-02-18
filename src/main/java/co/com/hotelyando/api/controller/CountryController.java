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
import co.com.hotelyando.database.model.Country;
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
	public ResponseEntity<String> save(@RequestBody Country country){
		
		String messageReturn = countryBusiness.save(country);
		
		if(StringUtils.isEmpty(messageReturn)) {
			return new ResponseEntity<String>(messageReturn, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(messageReturn, HttpStatus.OK);
		}
	}
}
