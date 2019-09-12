package co.com.hotelyando.core.services;

import java.util.List;

import co.com.hotelyando.database.model.Plan;

public interface IPlanService {
	
	String registrarPlan(Plan plan);
	List<Plan> consultarPlanesPorHotel(Integer hotelId);
	Plan consultarPlanPorHotel(Integer hotelId, Integer planId);

}
