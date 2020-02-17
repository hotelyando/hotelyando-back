package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.RoleService;
import co.com.hotelyando.database.model.Role;

@Service
public class RoleBusiness {
	
	private final RoleService roleService;
	
	public RoleBusiness(RoleService roleService) {
		this.roleService = roleService;
	}
	
	public String save(Role role) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = roleService.save(role);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return messageReturn;
	}
	
	public List<Role> findAll() {
		
		List<Role> roles = null;
		
		try {
			
			roles = roleService.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roles;
		
	}
}
