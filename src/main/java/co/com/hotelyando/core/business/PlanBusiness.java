package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PlanService;
import co.com.hotelyando.database.model.Plan;
import co.com.hotelyando.database.model.Usuario;

@Service
public class PlanBusiness {

	
private final PlanService planService;
	
	public PlanBusiness(PlanService planService) {
		this.planService = planService;
	}
	
	public String registrarPlan(Plan plan, Usuario usuario) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = planService.registrarPlan(plan);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Plan> consultarPlansPorHotel(Usuario usuario) {
		
		List<Plan> plans = null;
		
		try {
			
			plans = planService.consultarPlanesPorHotel(usuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return plans;
	}

	public Plan consultarPlanPorHotel(Usuario usuario, String planId) {
		
		Plan plan = null;
		
		try {
			
			plan = planService.consultarPlanPorHotel(usuario.getHotelId(), planId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return plan;
	}


}
