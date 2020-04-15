package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Country;
import co.com.hotelyando.database.repository.ICountryRepository;

@Repository
public class CountryDao {
	
	private final ICountryRepository iCountryRepository;
	
	
	public CountryDao(ICountryRepository iCountryRepository) {
		this.iCountryRepository = iCountryRepository;
	}
	
	
	public void save(Country country) throws MongoException, Exception {
		iCountryRepository.save(country);
	}
	
	
	public void update(Country country) throws MongoException, Exception {
		iCountryRepository.save(country);
	}
	
	
	public List<Country> findAll() throws MongoException, Exception {
		
		List<Country> countries = iCountryRepository.findAll();
		
		return countries;
	}
	
	public Country findByNombre(String nameCountry) throws MongoException, Exception {
	
		Country country = iCountryRepository.findByName(nameCountry);
		
		return country;
	}

}
