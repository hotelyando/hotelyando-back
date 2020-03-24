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
	
	public String save(Plan plan) throws Exception {
		
		iPlanRepository.save(plan);
		
		return "Ok";
	}

	public List<Plan> findByHotelId(String hotelId) throws Exception {
		
		List<Plan> plans;
		plans = iPlanRepository.findByHotelId(hotelId);
		
		return plans;
	}

	public Plan findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Plan plan = iPlanRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return plan;
	}

}
