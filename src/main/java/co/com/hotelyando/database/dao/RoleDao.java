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

	public void save(Role role) throws MongoException, Exception {
		iRoleRepository.save(role);
	}
	
	public void update(Role role) throws MongoException, Exception {
		iRoleRepository.save(role);
	}
	
	public List<Role> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Role> roles = iRoleRepository.findAll();
		
		return roles;
	}
	
	public Role findByName(String name) throws MongoException, Exception {
		
		Role role = iRoleRepository.findByName(name);
		
		return role;
	}
	
}
