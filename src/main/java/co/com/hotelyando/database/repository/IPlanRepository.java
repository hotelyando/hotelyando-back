package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Plan;

public interface IPlanRepository extends MongoRepository<Plan, Integer> {
	
	List<Plan> findByHotelId(Integer hotelId);
	Plan findByHotelIdAndPlanId(Integer hotelId, Integer planId);

}
