package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

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
		
		String retornoRespuesta = "";
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			retornoRespuesta = planBusiness.registrarPlan(plan, usuario);
		}catch (Exception e) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> consultarPlanPorHotel(@PathVariable Integer planId, @RequestHeader Map<String, String> headers){
		
		Plan plan = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			plan = planBusiness.consultarPlanPorHotel(usuario, planId);
			
		}catch (Exception e) {
			return new ResponseEntity<Plan>(plan, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<Plan>(plan, HttpStatus.OK);
		
	}
	
	@GetMapping("/plan")
	public ResponseEntity<List<Plan>> consultarPlansPorHotel(@RequestHeader Map<String, String> headers){
		
		List<Plan> plans = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			plans = planBusiness.consultarPlansPorHotel(usuario);
			
		}catch (Exception e) {
			return new ResponseEntity<List<Plan>>(plans, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<List<Plan>>(plans, HttpStatus.OK);
		
		
	}

}
