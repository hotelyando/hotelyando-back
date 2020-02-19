package co.com.hotelyando.database.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Hotel;

@JaversSpringDataAuditable
public interface IHotelRepository extends MongoRepository<Hotel, String> {
	
	Hotel findByUuid(String uuid);

}
