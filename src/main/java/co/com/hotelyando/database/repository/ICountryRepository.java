package co.com.hotelyando.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Country;

public interface ICountryRepository extends MongoRepository<Country, String> {
	
	

}
