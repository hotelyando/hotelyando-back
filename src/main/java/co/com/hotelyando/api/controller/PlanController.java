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

import co.com.hotelyando.core.business.PlanBusiness;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Plan;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Plan")
public class PlanController {
	
	private final PlanBusiness planBusiness;
	
	private Utilities utilities;
	private User user;
	
	public PlanController(PlanBusiness planBusiness) {
		this.planBusiness = planBusiness;
		
		utilities = new Utilities();
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> registrarPlan(@RequestBody Plan plan, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		String retornoRespuesta = planBusiness.registrarPlan(plan, user);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> consultarPlanPorHotel(@PathVariable String planId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		Plan plan = planBusiness.consultarPlanPorHotel(user, planId);
			
		if(plan == null) {
			return new ResponseEntity<Plan>(plan, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Plan>(plan, HttpStatus.OK);
		}
	}
	
	@GetMapping("/plan")
	public ResponseEntity<List<Plan>> consultarPlansPorHotel(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariables.TOKEN_HEADER);
			
		List<Plan> plans = planBusiness.consultarPlansPorHotel(user);
			
		if(plans == null) {
			return new ResponseEntity<List<Plan>>(plans, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Plan>>(plans, HttpStatus.OK);
		}
	}

}
