package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.IPlanDao;
import co.com.hotelyando.database.model.Plan;

@Service
public class PlanService implements IPlanService {

	@Autowired
	private IPlanDao iPlanDao;
	
	@Override
	public String registrarPlan(Plan plan) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = iPlanDao.registrarPlan(plan);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
	}

	@Override
	public List<Plan> consultarPlanesPorHotel(Integer hotelId) {
		
		List<Plan> plans = null;
		
		try {
			
			plans = iPlanDao.consultarPlanesPorHotel(hotelId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return plans;
	}

	@Override
	public Plan consultarPlanPorHotel(Integer hotelId, Integer planId) {
		
		Plan plan = null;
		
		try {
			
			plan = iPlanDao.consultarPlanPorHotel(hotelId, planId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return plan;
	}

}
