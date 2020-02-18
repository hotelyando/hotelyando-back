package co.com.hotelyando.core.services;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.CountryDao;
import co.com.hotelyando.database.model.Country;

@Service
public class CountryService {
	
	private final CountryDao countryDao;
	
	public CountryService(CountryDao countryDao) {
		this.countryDao = countryDao;
	}
	
	public String save(Country country) throws Exception {
		
		String messageReturn = "";
		
		messageReturn = countryDao.save(country);
		
		return messageReturn;
		
	}
	

}
