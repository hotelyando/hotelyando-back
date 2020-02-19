package co.com.hotelyando.database.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Country;

@JaversSpringDataAuditable
public interface ICountryRepository extends MongoRepository<Country, String> {
	
	

}
