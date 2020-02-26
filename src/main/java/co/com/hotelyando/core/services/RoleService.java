package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.PrintEntity;
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
		
		if(StringUtils.isBlank(role.getUuid())) {
			messageReturn = PrintEntity.ROLE_ID;
		}else if(StringUtils.isBlank(role.getHotelId())) {
			messageReturn = PrintEntity.ROLE_HOTEL_ID;
		}else if(StringUtils.isBlank(role.getName())) {
			messageReturn = PrintEntity.ROLE_NAME;
		}else if(StringUtils.isBlank(role.getDescription())) {
			messageReturn = PrintEntity.ROLE_DESCRIPTION;
		}else if(role.getPermits() == null) {
			messageReturn = PrintEntity.ROLE_PERMITS;
		}else {
			roleDao.save(role);
		}
		
		//Se debe validar si el nombre es unico dentro del HOTEL.
		
		return messageReturn;
	}
	
	public String update(Role role) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(role.getUuid())) {
			messageReturn = PrintEntity.ROLE_ID;
		}else if(StringUtils.isBlank(role.getHotelId())) {
			messageReturn = PrintEntity.ROLE_HOTEL_ID;
		}else if(StringUtils.isBlank(role.getName())) {
			messageReturn = PrintEntity.ROLE_NAME;
		}else if(StringUtils.isBlank(role.getDescription())) {
			messageReturn = PrintEntity.ROLE_DESCRIPTION;
		}else if(role.getPermits() == null) {
			messageReturn = PrintEntity.ROLE_PERMITS;
		}else {
			roleDao.update(role);
		}
		
		//Se debe validar si el nombre es unico dentro del HOTEL.
		
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
