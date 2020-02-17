package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.InvoiceBusiness;
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
	public ResponseEntity<String> registrarFactura(@RequestBody Invoice invoice, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		String serviceResponse = invoiceBusiness.save(invoice, user);
		
		if(StringUtils.isEmpty(serviceResponse)) {
			return new ResponseEntity<String>(serviceResponse, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(serviceResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping("/invoice/{invoiceId}")
	public ResponseEntity<Invoice> findByHotelIdAndUuid(@PathVariable String invoiceId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		Invoice	invoice = invoiceBusiness.findByHotelIdAndUuid(user, invoiceId);
			
		if(invoice == null) {
			return new ResponseEntity<Invoice>(invoice, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
		}
	}
	
	@GetMapping("/invoice")
	public ResponseEntity<List<Invoice>> findByHotelId(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		List<Invoice> invoices = invoiceBusiness.findByHotelId(user);
			
		if(invoices == null) {
			return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
		}
	}


}
