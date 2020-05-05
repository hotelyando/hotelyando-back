package co.com.hotelyando.core.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.services.HotelService;
import co.com.hotelyando.core.services.ParameterService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.model.Parameter;
import co.com.hotelyando.database.model.User;

@Service
public class ParameterBusiness {

	@Autowired
	private MessageSource messageSource;
	
	private final ParameterService parameterService;
	
	private ServiceResponse<Parameter> serviceResponse;
	private Utilities utilities = null;
	private Generic<Parameter> generic = null;
	
	public ParameterBusiness(ParameterService parameterService) {
		this.parameterService = parameterService;
		
		serviceResponse = new ServiceResponse<Parameter>();
		utilities = new Utilities();
		generic = new Generic<Parameter>();
	}
	
	public ServiceResponse<Parameter> save(Parameter parameter) {
		
		String messageReturn = "";
		
		try {
			
			parameter.setUuid(utilities.generadorId());
			
			messageReturn = parameterService.save(parameter);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(parameter, PrintVariable.NEGOCIO, messageSource.getMessage("parameter.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				parameter.setUuid("");
				serviceResponse = generic.messageReturn(parameter, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}

	
	public ServiceResponse<Parameter> update(Parameter parameter) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = parameterService.update(parameter);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(parameter, PrintVariable.NEGOCIO, messageSource.getMessage("parameter.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(parameter, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	
	
	public ServiceResponse<Parameter> findByHotelId(User user) {
				
		try {
			
			Parameter parameter = parameterService.findByHotelId(user.getHotelId());
			
			if(parameter != null) {
				serviceResponse = generic.messageReturn(parameter, PrintVariable.NEGOCIO, messageSource.getMessage("parameter.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("parameter.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	
	
	public ServiceResponse<Parameter> findByUuid(String uuid) {
		
		try {
			
			Parameter parameter = parameterService.findByUuid(uuid);
			
			if(parameter != null) {
				serviceResponse = generic.messageReturn(parameter, PrintVariable.NEGOCIO, messageSource.getMessage("hotel.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("hotel.not_content", null, LocaleContextHolder.getLocale()));
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
