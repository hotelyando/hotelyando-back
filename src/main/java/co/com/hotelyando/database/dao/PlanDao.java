package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Plan;
import co.com.hotelyando.database.repository.IPlanRepository;

@Repository
public class PlanDao{
	
	private final IPlanRepository iPlanRepository;
	
	public PlanDao(IPlanRepository iPlanRepository) {
		this.iPlanRepository = iPlanRepository;
	}
	
	public String registrarPlan(Plan plan) throws Exception {
		
		iPlanRepository.save(plan);
		
		return "Ok";
	}

	public List<Plan> consultarPlanesPorHotel(String hotelId) throws Exception {
		
		List<Plan> plans = null;
		plans = iPlanRepository.findByHotelId(hotelId);
		
		return plans;
	}

	public Plan consultarPlanPorHotel(String hotelId, String planId) throws Exception {
		
		Plan plan = null;
		plan = iPlanRepository.findByHotelIdAndPlanId(hotelId, planId);
		
		return plan;
	}

}
