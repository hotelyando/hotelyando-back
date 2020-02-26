package co.com.hotelyando.core.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.PrintEntity;
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
		
		if(StringUtils.isBlank(country.getUuid())) {
			messageReturn = PrintEntity.COUNTRY_ID;
		}else if(StringUtils.isBlank(country.getCode())) {
			messageReturn = PrintEntity.COUNTRY_CODE;
		}else if(StringUtils.isBlank(PrintEntity.COUNTRY_NAME)) {
			messageReturn = PrintEntity.COUNTRY_NAME;
		}else {
			countryDao.save(country);
		}
		
		return messageReturn;
		
	}
	
	public String update(Country country) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(country.getUuid())) {
			messageReturn = PrintEntity.COUNTRY_ID;
		}else if(StringUtils.isBlank(country.getCode())) {
			messageReturn = PrintEntity.COUNTRY_CODE;
		}else if(StringUtils.isBlank(PrintEntity.COUNTRY_NAME)) {
			messageReturn = PrintEntity.COUNTRY_NAME;
		}else {
			countryDao.update(country);
		}
		
		return messageReturn;
		
	}
	

}
