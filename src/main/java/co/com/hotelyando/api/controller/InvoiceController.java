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
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.InvoiceBusiness;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Invoice;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Invoice")
public class InvoiceController {
	
	private final InvoiceBusiness invoiceBusiness;
	
	private Utilities utilities;
	private User user;
	
	public InvoiceController(InvoiceBusiness invoiceBusiness) {
		this.invoiceBusiness = invoiceBusiness;
		
		utilities = new Utilities();
	}
	
	@PostMapping("/invoice")
	public ResponseEntity<ServiceResponse<Invoice>> save(@RequestBody Invoice invoice, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponse<Invoice> serviceResponse = invoiceBusiness.save(invoice, user);
		
		return new ResponseEntity<ServiceResponse<Invoice>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@PutMapping("/invoice")
	public ResponseEntity<ServiceResponse<Invoice>> update(@RequestBody Invoice invoice, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponse<Invoice> serviceResponse = invoiceBusiness.update(invoice, user);
		
		return new ResponseEntity<ServiceResponse<Invoice>>(serviceResponse, HttpStatus.OK);
	}
	
	@GetMapping("/invoice/{invoiceId}")
	public ResponseEntity<ServiceResponse<Invoice>> findByHotelIdAndUuid(@PathVariable String invoiceId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponse<Invoice> serviceResponse = invoiceBusiness.findByHotelIdAndUuid(user, invoiceId);
			
		return new ResponseEntity<ServiceResponse<Invoice>>(serviceResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("/invoice")
	public ResponseEntity<ServiceResponses<Invoice>> findByHotelId(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		ServiceResponses<Invoice> serviceResponses = invoiceBusiness.findByHotelId(user);
			
		return new ResponseEntity<ServiceResponses<Invoice>>(serviceResponses, HttpStatus.OK);
		
	}


}
