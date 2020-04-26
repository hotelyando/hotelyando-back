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
import co.com.hotelyando.core.utilities.PrintVariable;
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
	private ServiceResponses<Role> serviceResponses;
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
			
			messageReturn = roleService.save(role);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(role, PrintVariable.NEGOCIO, messageSource.getMessage("role.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				role.setUuid("");
				serviceResponse = generic.messageReturn(role, PrintVariable.VALIDACION, messageReturn);
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
	 * Método que actualiza un rol de un hotel 
	 * @return ServiceResponse<Role>
	 */
	public ServiceResponse<Role> update(Role role, User user) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = roleService.update(role);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(role, PrintVariable.NEGOCIO, messageSource.getMessage("role.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(role, PrintVariable.VALIDACION, messageReturn);
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
	 * Método que retorna un rol por su nombre
	 * @return ServiceResponse<Role>
	 */
	public ServiceResponse<Role> findByName(User user, String name) {
		
		try {
			
			Role role = roleService.findByName(name);
			
			if(role != null) {
				serviceResponse = generic.messageReturn(role, PrintVariable.NEGOCIO, messageSource.getMessage("role.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("role.not_content", null, LocaleContextHolder.getLocale()));
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
	 * Método que retorna una lista de roles de un hotel
	 * @return ServiceResponses<Role>
	 */
	public ServiceResponses<Role> findByHotelId(User user) {
		
		try {
			
			List<Role> roles = roleService.findByHotelId(user.getHotelId());
			
			if(roles != null) {
				serviceResponses = generic.messagesReturn(roles, PrintVariable.NEGOCIO, messageSource.getMessage("role.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("role.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
		
		return serviceResponses;
		
	}
}
