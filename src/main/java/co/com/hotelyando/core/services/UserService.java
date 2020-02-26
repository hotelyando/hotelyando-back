package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.database.dao.UserDao;
import co.com.hotelyando.database.model.User;

@Service
public class UserService {

	private final UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String save(User user) throws Exception {
		
		String retornoMensaje = "";
	
		if(StringUtils.isBlank(user.getUuid())) {
			retornoMensaje = PrintEntity.VALIDACION_ID_USUARIO;
		}else if(StringUtils.isBlank(user.getHotelId())) {
			retornoMensaje = PrintEntity.VALIDACION_HOTEL_ID;
		}else if(StringUtils.isBlank(user.getRol())) {
			retornoMensaje = PrintEntity.VALIDACION_ROL;
		}else if(StringUtils.isBlank(user.getPersonId())) {
			retornoMensaje = PrintEntity.VALIDACION_PERSONA;
		}else if(StringUtils.isBlank(user.getUser())) {
			retornoMensaje = PrintEntity.VALIDACION_USUARIO;
		}else if(StringUtils.isBlank(user.getPassword())) {
			retornoMensaje = PrintEntity.VALIDACION_CONTRASENA;
		}else {
			userDao.save(user);
		}
		
		return retornoMensaje;
	}

	
	public List<User> findByHotelId(String hotelId) throws Exception {
		
		List<User> users = null;
		users = userDao.findByHotelId(hotelId);
		
		return users;
	}

	
	public User findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		User user = null;
		user = userDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return user;
	}

	
	public User findByUserAndPassword(String userLogin, String password) throws Exception {
		
		User user = new User();
		
		if(StringUtils.isBlank(userLogin)) {
			user.setUuid(PrintVariables.VALIDACION);
		}else if(StringUtils.isBlank(password)) {
			user.setUuid(PrintVariables.VALIDACION);
		}else {
			user = userDao.findByUserAndPassword(userLogin, password);
		}
		
		return user;
	}
}
