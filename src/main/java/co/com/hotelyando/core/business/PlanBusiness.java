package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.PlanService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Plan;
import co.com.hotelyando.database.model.User;

@Service
public class PlanBusiness {

	
	private final PlanService planService;
	private ServiceResponse<Plan> serviceResponse;
	private ServiceResponses<Plan> serviceResponses;
	private Utilities utilities = null;
	private Generic<Plan> generic = null;
	
	private String messageReturn;
	
	public PlanBusiness(PlanService planService) {
		this.planService = planService;
		
		serviceResponse = new ServiceResponse<Plan>();
		serviceResponses = new ServiceResponses<Plan>();
		utilities = new Utilities();
		generic = new Generic<Plan>();
		
	}
	
	public String save(Plan plan, User user) {
		
		try {
			
			plan.setUuid(utilities.generadorId());
			plan.setHotelId(user.getHotelId());
			
			messageReturn = planService.save(plan);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return messageReturn;
	}

	public ServiceResponses<Plan> findByHotelId(User user) {
		
		try {
			
			List<Plan> plans = planService.findByHotelId(user.getHotelId());
			
			serviceResponses = generic.messagesReturn(plans, PrintVariable.NEGOCIO, "Ok");
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}

	public ServiceResponse<Plan> findByHotelIdAndUuid(User user, String uuid) {
		
		try {
			
			Plan plan = planService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			serviceResponse = generic.messageReturn(plan, PrintVariable.NEGOCIO, "Ok");
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}


}
