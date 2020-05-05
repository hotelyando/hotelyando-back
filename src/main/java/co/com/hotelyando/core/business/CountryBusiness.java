package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.CountryService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Country;

@Service
public class CountryBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
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
	
	
	/*
	 * M�todo para el registro de paises
	 * @return ServiceResponse<Country>
	 */
	public ServiceResponse<Country> save(Country country) {
		
		String messageReturn = "";
		
		try {
			
			country.setUuid(utilities.generadorId());
			
			messageReturn = countryService.save(country);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(country, PrintVariable.NEGOCIO, messageSource.getMessage("country.register_ok", new String[] {country.getName()}, LocaleContextHolder.getLocale()));
			}else {
				country.setUuid("");
				serviceResponse = generic.messageReturn(country, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	
	
	/*
	 * M�todo para la actualizaci�n de paises
	 * @return ServiceResponse<Country>
	 */
	public ServiceResponse<Country> update(Country country) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = countryService.update(country);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(country, PrintVariable.NEGOCIO, messageSource.getMessage("country.update_ok", new String[] {country.getName()}, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(country, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	
	
	public ServiceResponses<Country> findAll() {
		
		String messageReturn = "";
		
		try {
			
			List<Country> countries = countryService.findAll();
			
			if(countries != null) {
				serviceResponses = generic.messagesReturn(countries, PrintVariable.NEGOCIO, messageSource.getMessage("country.find_ok", new String[] {countries.get(0).getName()}, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}


	public ServiceResponse<Country> findByNombre(String nameCountry) {
		
		String messageReturn = "";
		
		try {
			
			Country country = countryService.findByNombre(nameCountry);
			
			if(country != null) {
				serviceResponse = generic.messageReturn(country, PrintVariable.NEGOCIO, messageSource.getMessage("country.find_ok", new String[] {country.getName()}, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	
	
	/*
	 * Método para la eliminacion de paises
	 * @return ServiceResponse<Country>
	 */
	public ServiceResponse<Country> delete(String uuid) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = countryService.delete(uuid);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("country.delete_ok", new String[] {""}, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	

}
