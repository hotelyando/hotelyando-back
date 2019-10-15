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
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Plan;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PlanController {
	
private PlanBusiness planBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public PlanController(PlanBusiness planBusiness) {
		this.planBusiness = planBusiness;
	}
	
	public PlanController() {
		utilidades = new Utilidades();
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> registrarPlan(@RequestBody Plan plan, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		String retornoRespuesta = planBusiness.registrarPlan(plan, usuario);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> consultarPlanPorHotel(@PathVariable Integer planId, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		Plan plan = planBusiness.consultarPlanPorHotel(usuario, planId);
			
		if(plan == null) {
			return new ResponseEntity<Plan>(plan, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Plan>(plan, HttpStatus.OK);
		}
	}
	
	@GetMapping("/plan")
	public ResponseEntity<List<Plan>> consultarPlansPorHotel(@RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		List<Plan> plans = planBusiness.consultarPlansPorHotel(usuario);
			
		if(plans == null) {
			return new ResponseEntity<List<Plan>>(plans, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Plan>>(plans, HttpStatus.OK);
		}
	}

}
