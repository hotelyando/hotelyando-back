package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Plan;
import co.com.hotelyando.database.repository.IPlanRepository;

@Repository
public class PlanDao implements IPlanDao{
	
	@Autowired
	private IPlanRepository iPlanRepository;
	
	@Override
	public String registrarPlan(Plan plan) throws Exception {
		
		iPlanRepository.save(plan);
		
		return null;
	}

	@Override
	public List<Plan> consultarPlanesPorHotel(Integer hotelId) throws Exception {
		
		List<Plan> plans = null;
		
		plans = iPlanRepository.findByHotelId(hotelId);
		
		return plans;
	}

	@Override
	public Plan consultarPlanPorHotel(Integer hotelId, Integer planId) throws Exception {
		
		Plan plan = null;
		
		plan = iPlanRepository.findByHotelIdAndPlanId(hotelId, planId);
		
		return plan;
	}

}
