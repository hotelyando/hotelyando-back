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
	
	public String save(Plan plan) throws Exception {
		
		String messageReturn = planDao.save(plan);
		
		return messageReturn;
	}

	public List<Plan> findByHotelId(String hotelId) throws Exception {
		
		List<Plan> plans = planDao.findByHotelId(hotelId);
		
		return plans;
	}

	public Plan findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Plan plan = planDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return plan;
	}

}
