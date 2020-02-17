package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.RoleDao;
import co.com.hotelyando.database.model.Role;

@Service
public class RoleService {
	
	private final RoleDao roleDao;
	
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public String save(Role role) throws Exception {
		
		String messageReturn = "";
		
		messageReturn = roleDao.save(role);
		
		return messageReturn;
	}
	
	public List<Role> findAll() throws Exception {
		
		List<Role> roles = null;
		roles = roleDao.findAll();
		
		return roles;
		
	}
	
	public Role findByName(String name) throws Exception {
		
		Role role = null;
		
		role = roleDao.findByName(name);
		
		return role;
		
	}

}
