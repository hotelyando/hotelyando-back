package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PlanService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.database.model.Plan;
import co.com.hotelyando.database.model.Usuario;

@Service
public class PlanBusiness {

	
private final PlanService planService;
	
	private Genericos<Usuario> genericos;
	private Usuario objetoUsuario;
	
	public PlanBusiness(PlanService planService) {
		this.planService = planService;
		
		genericos = new Genericos<>();
	}
	
	public String registrarPlan(Plan plan, String usuario) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = planService.registrarPlan(plan);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Plan> consultarPlansPorHotel(String usuario) {
		
		List<Plan> plans = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			plans = planService.consultarPlanesPorHotel(objetoUsuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return plans;
	}

	public Plan consultarPlanPorHotel(String usuario, Integer planId) {
		
		Plan plan = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			plan = planService.consultarPlanPorHotel(objetoUsuario.getHotelId(), planId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return plan;
	}


}
