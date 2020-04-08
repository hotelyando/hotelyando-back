package co.com.hotelyando.database.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Parameter;

@JaversSpringDataAuditable
public interface IParameterRepository extends MongoRepository<Parameter, String> {
	
	Parameter findByHotelId(String hotelId);
	Parameter findByUuid(String uuid);
	

}
