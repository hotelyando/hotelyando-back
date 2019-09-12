package co.com.hotelyando.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.services.IPlanService;
import co.com.hotelyando.database.model.Plan;

@RestController
public class PlanController {
	
	@Autowired
	private IPlanService iPlanService;
	
	@PostMapping("/plan")
	public ResponseEntity<String> registrarPlan(@RequestBody Plan plan){
		
		String retornoRespuesta = "";
		
		retornoRespuesta = iPlanService.registrarPlan(plan); 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/plan/{hotelId}/{planId}")
	public ResponseEntity<Plan> consultarPlanPorHotel(@PathVariable Integer hotelId, Integer planId){
		
		Plan plan = null;
		
		plan = iPlanService.consultarPlanPorHotel(hotelId, planId);
		
		return new ResponseEntity<Plan>(plan, HttpStatus.OK);
	}
	
	@GetMapping("/plan/{hotelId}")
	public ResponseEntity<List<Plan>> consultarPlansPorHotel(@PathVariable Integer hotelId){
		
		List<Plan> planes = null;
		
		planes = iPlanService.consultarPlanesPorHotel(hotelId);
		
		return new ResponseEntity<List<Plan>>(planes, HttpStatus.OK);
	}


}
