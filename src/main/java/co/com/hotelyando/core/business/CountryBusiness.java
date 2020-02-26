package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.CountryService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Country;
import co.com.hotelyando.database.model.Room;

@Service
public class CountryBusiness {
	
	private final CountryService countryService;
	private Utilities utilities = null;
	private ServiceResponse<Country> serviceResponse;
	private ServiceResponses<Country> serviceResponses;
	private Generic<Country> generic = null;
	
	
	public CountryBusiness(CountryService countryService) {
		this.countryService = countryService;
		
		utilities = new Utilities();
		serviceResponse = new ServiceResponse<Country>();
		serviceResponses = new ServiceResponses<Country>();
		generic = new Generic<Country>();
	}
	
	public ServiceResponse<Country> save(Country country) {
		
		String messageReturn = "";
		
		try {
			
			country.setUuid(utilities.generadorId());
			
			messageReturn = countryService.save(country);
			
			serviceResponse = generic.messageReturn(country, PrintVariables.NEGOCIO, "Registro Bien");
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	
	
	public ServiceResponse<Country> update(Country country) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = countryService.update(country);
			
			serviceResponse = generic.messageReturn(country, PrintVariables.NEGOCIO, "Registro Bien");
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}

}
