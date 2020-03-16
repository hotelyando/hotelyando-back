package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.RoleDao;
import co.com.hotelyando.database.model.Role;

@Service
public class RoleService {
	
	@Autowired
	private MessageSource messageSource;
	
	private RegularExpression regularExpression = null;
	
	private final RoleDao roleDao;
	
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
		
		regularExpression = new RegularExpression();
	}
	
	
	/*
	 * Método que registra un rol en un hotel
	 * @return String
	 */
	public String save(Role role) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(role.getUuid())) {
			messageReturn = messageSource.getMessage("role.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(role.getHotelId())) {
			messageReturn = messageSource.getMessage("role.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(role.getName())) {
			messageReturn = messageSource.getMessage("role.name", null, LocaleContextHolder.getLocale());
		}else if(validateRoleName(role.getHotelId(), role.getName())) {
			messageReturn = messageSource.getMessage("role.name_unique", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(role.getDescription())) {
			messageReturn = messageSource.getMessage("role.description", null, LocaleContextHolder.getLocale());
		}else if(role.getPermits() == null) {
			messageReturn = messageSource.getMessage("role.permits", null, LocaleContextHolder.getLocale());
		}else {
			roleDao.save(role);
		}
		
		//Se debe validar si el nombre es unico dentro del HOTEL.
		
		return messageReturn;
	}
	
	
	/*
	 * Método que actualiza un rol en un hotel
	 * @return String
	 */
	public String update(Role role) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(role.getUuid())) {
			messageReturn = messageSource.getMessage("role.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(role.getHotelId())) {
			messageReturn = messageSource.getMessage("role.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(role.getName())) {
			messageReturn = messageSource.getMessage("role.name", null, LocaleContextHolder.getLocale());
		}else if(validateRoleName(role.getHotelId(), role.getName())) {
			messageReturn = messageSource.getMessage("role.name_unique", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(role.getDescription())) {
			messageReturn = messageSource.getMessage("role.description", null, LocaleContextHolder.getLocale());
		}else if(role.getPermits() == null) {
			messageReturn = messageSource.getMessage("role.permits", null, LocaleContextHolder.getLocale());
		}else {
			roleDao.update(role);
		}
		
		//Se debe validar si el nombre es unico dentro del HOTEL.
		
		return messageReturn;
	}
	
	
	/*
	 * Método que retorna la lista de roles de un hotel
	 * @return List<Role>
	 */
	public List<Role> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Role> roles = null;
		roles = roleDao.findByHotelId(hotelId);
		
		return roles;
		
	}
	
	
	/*
	 * Método que retorna un rol por el nombre
	 * @return Role
	 */
	public Role findByName(String hotelId, String name) throws MongoException, Exception {
		
		Role role = null;
		
		role = roleDao.findByName(hotelId, name);
		
		return role;
		
	}
	
	
	/*
	 * Método que valida si existe un rol en el hotel
	 * @return Role
	 */
	public Boolean validateRoleName(String hotelId, String name) throws MongoException, Exception {
		
		Role role = null;
		
		role = roleDao.findByName(hotelId, name);
		
		if (role != null) {
			return true;
		}else {
			return false;
		}
	}

}
