package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Role;
import co.com.hotelyando.database.repository.IRoleRepository;

@Repository
public class RoleDao {
	
	private final IRoleRepository iRoleRepository;
	
	public RoleDao(IRoleRepository iRoleRepository) {
		this.iRoleRepository = iRoleRepository;
	}

	public String save(Role role) throws MongoException, Exception {
		
		iRoleRepository.save(role);
		
		return "Ok";
	}
	
	public List<Role> findAll() throws MongoException, Exception {
		
		List<Role> roles = null;
		roles = iRoleRepository.findAll();
		
		return roles;
		
	}
	
	public Role findByName(String name) throws MongoException, Exception {
		
		Role role = null;
		
		role = iRoleRepository.findByName(name);
		
		return role;
		
	}
	
}
