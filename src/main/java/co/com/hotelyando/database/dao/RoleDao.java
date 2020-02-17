package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Role;
import co.com.hotelyando.database.repository.IRoleRepository;

@Repository
public class RoleDao {
	
	private final IRoleRepository iRoleRepository;
	
	public RoleDao(IRoleRepository iRoleRepository) {
		this.iRoleRepository = iRoleRepository;
	}

	public String save(Role role) throws Exception {
		
		iRoleRepository.save(role);
		
		return "Ok";
	}
	
	public List<Role> findAll() throws Exception {
		
		List<Role> roles = null;
		roles = iRoleRepository.findAll();
		
		return roles;
		
	}
	
	public Role findByName(String name) throws Exception {
		
		Role role = null;
		
		role = iRoleRepository.findByName(name);
		
		return role;
		
	}
	
}
