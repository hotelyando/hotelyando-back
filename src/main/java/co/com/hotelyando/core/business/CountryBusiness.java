package co.com.hotelyando.core.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.services.CountryService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Country;

@Service
public class CountryBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
	private final CountryService countryService;
	private Utilities utilities = null;
	private ServiceResponse<Country> serviceResponse;
	private Generic<Country> generic = null;
	
	
	public CountryBusiness(CountryService countryService) {
		this.countryService = countryService;
		
		utilities = new Utilities();
		serviceResponse = new ServiceResponse<Country>();
		generic = new Generic<Country>();
	}
	
	
	/*
	 * Método para el registro de paises
	 * @return ServiceResponse<Country>
	 */
	public ServiceResponse<Country> save(Country country) {
		
		String messageReturn = "";
		
		try {
			
			country.setUuid(utilities.generadorId());
			
			messageReturn = countryService.save(country);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(country, PrintVariables.NEGOCIO, messageSource.getMessage("country.register_ok", new String[] {country.getName()}, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(country, PrintVariables.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	
	
	/*
	 * Método para la actualización de paises
	 * @return ServiceResponse<Country>
	 */
	public ServiceResponse<Country> update(Country country) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = countryService.update(country);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(country, PrintVariables.NEGOCIO, messageSource.getMessage("country.update_ok", new String[] {country.getName()}, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(country, PrintVariables.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}

}
