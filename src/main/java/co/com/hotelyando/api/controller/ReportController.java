package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.ReportBusiness;
import co.com.hotelyando.core.model.SaleReport;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Report")
public class ReportController {

	private final ReportBusiness reportBusiness;
	
	private Utilities utilities;
	private User user;
	
	public ReportController(ReportBusiness reportBusiness) {
		this.reportBusiness = reportBusiness;
		
		utilities = new Utilities();
	}
	
	@GetMapping("/report")
	public ResponseEntity<ServiceResponses<SaleReport>> findByHotelIdAndCountryAndDate(@RequestParam(defaultValue = "") String client, @RequestParam(defaultValue = "") String nationality, @RequestParam(defaultValue = "") String initDate, @RequestParam(defaultValue = "") String endDate, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		ServiceResponses<SaleReport> serviceResponses = reportBusiness.findBySaleReport(client, nationality, initDate, endDate, user);
			
		return new ResponseEntity<ServiceResponses<SaleReport>>(serviceResponses, HttpStatus.OK);
		
	}
}
