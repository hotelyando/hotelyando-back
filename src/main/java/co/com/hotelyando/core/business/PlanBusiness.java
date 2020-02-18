package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PlanService;
import co.com.hotelyando.database.model.Plan;
import co.com.hotelyando.database.model.User;

@Service
public class PlanBusiness {

	
private final PlanService planService;
	
	public PlanBusiness(PlanService planService) {
		this.planService = planService;
	}
	
	public String save(Plan plan, User user) {
		
		String messageReturn = "";
		
		try {
			messageReturn = planService.save(plan);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return messageReturn;
	}

	public List<Plan> findByHotelId(User user) {
		
		List<Plan> plans = null;
		
		try {
			
			plans = planService.findByHotelId(user.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return plans;
	}

	public Plan findByHotelIdAndUuid(User user, String uuid) {
		
		Plan plan = null;
		
		try {
			
			plan = planService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return plan;
	}


}
