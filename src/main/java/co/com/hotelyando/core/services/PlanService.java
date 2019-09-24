package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.PlanDao;
import co.com.hotelyando.database.model.Plan;

@Service
public class PlanService {

	
	private final PlanDao planDao;
	
	public PlanService(PlanDao planDao) {
		this.planDao = planDao;
	}
	
	public String registrarPlan(Plan plan) throws Exception {
		
		String retornoMensaje = "";
		
		retornoMensaje = planDao.registrarPlan(plan);
		
		return retornoMensaje;
	}

	public List<Plan> consultarPlanesPorHotel(Integer hotelId) throws Exception {
		
		List<Plan> plans = null;
		plans = planDao.consultarPlanesPorHotel(hotelId);
		
		return plans;
	}

	public Plan consultarPlanPorHotel(Integer hotelId, Integer planId) throws Exception {
		
		Plan plan = null;
		plan = planDao.consultarPlanPorHotel(hotelId, planId);
		
		return plan;
	}

}
