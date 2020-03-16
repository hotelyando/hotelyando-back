package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.RoleService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Role;
import co.com.hotelyando.database.model.User;

@Service
public class RoleBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
	private final RoleService roleService;
	private Generic<Role> generic = null;
	private ServiceResponse<Role> serviceResponse;
	private Utilities utilities = null;
	
	
	public RoleBusiness(RoleService roleService) {
		this.roleService = roleService;
		
		generic = new Generic<Role>();
		serviceResponse = new ServiceResponse<Role>();
		utilities = new Utilities();
		
	}
	
	
	/*
	 * Método que registra un rol para un hotel
	 * @return ServiceResponse<Role>
	 */
	public ServiceResponse<Role> save(Role role, User user) {
		
		String messageReturn = "";
		
		try {
			
			role.setUuid(utilities.generadorId());
			role.setHotelId(user.getHotelId());
			
			messageReturn = roleService.save(role);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(role, PrintVariables.NEGOCIO, messageSource.getMessage("role.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageReturn);
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
	 * Método que actualiza un rol de un hotel 
	 * @return ServiceResponse<Role>
	 */
	public ServiceResponse<Role> update(Role role, User user) {
		
		String messageReturn = "";
		
		try {
			
			role.setHotelId(user.getHotelId());
			
			messageReturn = roleService.update(role);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(role, PrintVariables.NEGOCIO, messageSource.getMessage("role.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageReturn);
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
	 * Método que retorna un rol por su nombre
	 * @return ServiceResponse<Role>
	 */
	public ServiceResponse<Role> findByName(User user, String name) {
		
		Role role = null;
		ServiceResponse<Role> serviceResponse = null;
		
		try {
			
			role = roleService.findByName(user.getHotelId(), name);
			
			if(role != null) {
				serviceResponse = generic.messageReturn(role, PrintVariables.NEGOCIO, messageSource.getMessage("role.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, messageSource.getMessage("role.not_content", null, LocaleContextHolder.getLocale()));
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
	 * Método que retorna una lista de roles de un hotel
	 * @return ServiceResponses<Role>
	 */
	public ServiceResponses<Role> findByHotelId(User user) {
		
		List<Role> roles = null;
		ServiceResponses<Role> serviceResponses = null;
		
		try {
			
			roles = roleService.findByHotelId(user.getHotelId());
			
			if(roles != null) {
				serviceResponses = generic.messagesReturn(roles, PrintVariables.NEGOCIO, messageSource.getMessage("role.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariables.NEGOCIO, messageSource.getMessage("role.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
		
		return serviceResponses;
		
	}
}
