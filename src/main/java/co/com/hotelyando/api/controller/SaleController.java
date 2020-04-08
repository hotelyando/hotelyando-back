package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.SaleBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Sale")
public class SaleController {
	
	private final SaleBusiness saleBusiness;
	
	private Utilities utilities;
	private User user;
	
	public SaleController(SaleBusiness saleBusiness) {
		this.saleBusiness = saleBusiness;
		
		utilities = new Utilities();
	}
	
	@PostMapping("/sale")
	public ResponseEntity<ServiceResponse<Sale>> save(@RequestBody Sale sale, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponse<Sale> serviceResponse = saleBusiness.save(sale, user);
		
		return new ResponseEntity<ServiceResponse<Sale>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@PutMapping("/sale")
	public ResponseEntity<ServiceResponse<Sale>> update(@RequestBody Sale sale, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponse<Sale> serviceResponse = saleBusiness.update(sale, user);
		
		return new ResponseEntity<ServiceResponse<Sale>>(serviceResponse, HttpStatus.OK);
	}
	
	@GetMapping("/sale/{saleId}")
	public ResponseEntity<ServiceResponse<Sale>> findByHotelIdAndUuid(@PathVariable String saleId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponse<Sale> serviceResponse = saleBusiness.findByHotelIdAndUuid(user, saleId);
			
		return new ResponseEntity<ServiceResponse<Sale>>(serviceResponse, HttpStatus.OK);
		
	}
	
	/*@GetMapping("/sale")
	public ResponseEntity<ServiceResponses<Sale>> findByHotelId(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponses<Sale> serviceResponses = saleBusiness.findByHotelId(user);
			
		return new ResponseEntity<ServiceResponses<Sale>>(serviceResponses, HttpStatus.OK);
		
	}*/
	
	@GetMapping("/sale")
	public ResponseEntity<ServiceResponses<Sale>> findByHotelIdAndCountryAndDate(@RequestParam(defaultValue = "") String client, @RequestParam(defaultValue = "") String nationality, @RequestParam(defaultValue = "") String initDate, @RequestParam(defaultValue = "") String endDate, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponses<Sale> serviceResponses = saleBusiness.findByHotelIdAndCountryAndDate(client, nationality, initDate, endDate, user);
			
		return new ResponseEntity<ServiceResponses<Sale>>(serviceResponses, HttpStatus.OK);
		
	}


}
