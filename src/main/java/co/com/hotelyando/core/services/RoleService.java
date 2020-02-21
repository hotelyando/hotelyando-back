package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.database.dao.RoleDao;
import co.com.hotelyando.database.model.Role;

@Service
public class RoleService {
	
	private final RoleDao roleDao;
	
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public String save(Role role) throws MongoException, Exception {
		
		String messageReturn = "";
		
		messageReturn = roleDao.save(role);
		
		return messageReturn;
	}
	
	public List<Role> findAll() throws MongoException, Exception {
		
		List<Role> roles = null;
		roles = roleDao.findAll();
		
		return roles;
		
	}
	
	public Role findByName(String name) throws MongoException, Exception {
		
		Role role = null;
		
		role = roleDao.findByName(name);
		
		return role;
		
	}

}
