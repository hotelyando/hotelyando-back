package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.CountryService;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Country;

@Service
public class CountryBusiness {
	
	private final CountryService countryService;
	private Utilities utilities = null;
	
	public CountryBusiness(CountryService countryService) {
		this.countryService = countryService;
		utilities = new Utilities();
	}
	
	public String save(Country country) {
		
		String messageReturn = "";
		
		try {
			
			country.setUuid(utilities.generadorId());
			
			messageReturn = countryService.save(country);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return messageReturn;
		
	}

}
