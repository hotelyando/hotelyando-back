package co.com.hotelyando.database.dao;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Country;
import co.com.hotelyando.database.repository.ICountryRepository;

@Repository
public class CountryDao {
	
	private final ICountryRepository iCountryRepository;
	
	public CountryDao(ICountryRepository iCountryRepository) {
		this.iCountryRepository = iCountryRepository;
	}
	
	public String save(Country country) throws Exception {
		
		iCountryRepository.save(country);
		
		return "Ok";
		
	}
	
	

}
