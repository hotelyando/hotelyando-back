package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Plan;

@JaversSpringDataAuditable
public interface IPlanRepository extends MongoRepository<Plan, String> {
	
	List<Plan> findByHotelId(String hotelId);
	Plan findByHotelIdAndUuid(String hotelId, String uuid);

}
