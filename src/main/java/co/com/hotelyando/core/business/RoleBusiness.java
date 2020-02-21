package co.com.hotelyando.core.business;

import java.util.List;

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
	
	private final RoleService roleService;
	private Generic<Role> generic = null;
	private ServiceResponse<Role> serviceResponse;
	private ServiceResponses<Role> serviceResponses;
	private Utilities utilities = null;
	
	
	public RoleBusiness(RoleService roleService) {
		this.roleService = roleService;
		
		generic = new Generic<Role>();
		serviceResponse = new ServiceResponse<Role>();
		serviceResponses = new ServiceResponses<Role>();
		utilities = new Utilities();
		
	}
	
	public ServiceResponse<Role> save(Role role, User user) {
		
		String messageReturn = "";
		
		try {
			
			role.setUuid(utilities.generadorId());
			
			messageReturn = roleService.save(role);
			
			serviceResponse = generic.messageReturn(role, PrintVariables.NEGOCIO, "Registro correcto!.");
			
		}catch (MongoException be) {
			be.printStackTrace();
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	public ServiceResponses<Role> findAll() {
		
		List<Role> roles = null;
		ServiceResponses<Role> serviceResponses = null;
		
		try {
			
			roles = roleService.findAll();
			
			serviceResponses = generic.messagesReturn(roles, PrintVariables.NEGOCIO, "Ok"); 
			
		}catch (MongoException be) {
			be.printStackTrace();
		} catch (Exception e) {
			serviceResponses = generic.messagesReturn(roles, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}
}
