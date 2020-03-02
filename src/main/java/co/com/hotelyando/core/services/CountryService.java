package co.com.hotelyando.core.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.CountryDao;
import co.com.hotelyando.database.model.Country;

@Service
public class CountryService {
	
	@Autowired
	private MessageSource messageSource;
	
	private RegularExpression regularExpression = null;
	
	private final CountryDao countryDao;
	
	public CountryService(CountryDao countryDao) {
		this.countryDao = countryDao;
		
		regularExpression = new RegularExpression();
	}
	
	
	/*
	 * Método para el registro de paises
	 * @return string
	 */
	public String save(Country country) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(country.getUuid())) {
			messageReturn = messageSource.getMessage("country.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(country.getCode())) {
			messageReturn = messageSource.getMessage("country.code", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(country.getName())) {
			messageReturn = messageSource.getMessage("country.name", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateSpecialCharacters(country.getName())) {
			messageReturn = messageSource.getMessage("country.character", null, LocaleContextHolder.getLocale());
		}else if(nameValidate(country.getName())) {
			messageReturn = messageSource.getMessage("country.name_unique", null, LocaleContextHolder.getLocale());
		}else {
			countryDao.save(country);
		}
		
		return messageReturn;
	}
	
	
	/*
	 * Método para la actualización de paises
	 * @return string
	 */
	public String update(Country country) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(country.getUuid())) {
			messageReturn = messageSource.getMessage("country.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(country.getCode())) {
			messageReturn = messageSource.getMessage("country.code", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(country.getName())) {
			messageReturn = messageSource.getMessage("country.name", null, LocaleContextHolder.getLocale());
		}else if(nameValidate(country.getName())) {
			messageReturn = messageSource.getMessage("country.name_unique", null, LocaleContextHolder.getLocale());
		}else {
			countryDao.update(country);
		}
		
		return messageReturn;
	}
	
	
	/*
	 * Método para validar si el nombre existe en la base de datos
	 * @return boolean
	 */
	private boolean nameValidate(String nameCountry) throws MongoException, Exception {
		
		Country country = null;
		country = countryDao.findByNombre(nameCountry);
		
		if(country != null) {
			return true;
		}else {
			return false;
		}
	}
	

}
