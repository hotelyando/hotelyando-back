package co.com.hotelyando.database.dao;

import java.util.List;

import co.com.hotelyando.database.model.Plan;

public interface IPlanDao {
	
	String registrarPlan(Plan plan) throws Exception;
	List<Plan> consultarPlanesPorHotel(Integer hotelId) throws Exception;
	Plan consultarPlanPorHotel(Integer hotelId, Integer planId) throws Exception;

}
