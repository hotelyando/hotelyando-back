package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.EncryptionData;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.UserDao;
import co.com.hotelyando.database.model.User;

@Service
public class UserService {

	@Autowired
	private MessageSource messageSource;
	
	private RegularExpression regularExpression = null;
	private EncryptionData encryptionData = null;
	
	private final UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
		
		regularExpression = new RegularExpression();
	}
	
	
	/*
	 * Método para el registro de usuario en un hotel
	 * @return String
	 */
	public String save(User user) throws MongoException, Exception {
		
		String messagesReturn = "";
	
		if(StringUtils.isBlank(user.getUuid())) {
			messagesReturn = messageSource.getMessage("user.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getHotelId())) {
			messagesReturn = messageSource.getMessage("user.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getRol())) {
			messagesReturn = messageSource.getMessage("user.rol", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getPersonId())) {
			messagesReturn = messageSource.getMessage("user.person", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getUser())) {
			messagesReturn = messageSource.getMessage("user.login", null, LocaleContextHolder.getLocale());
		}else if(validateUser(user.getHotelId(), user.getUser())) {
			messagesReturn = messageSource.getMessage("user.login_unique", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateSpecialCharacters(user.getUser())) {
			messagesReturn = messageSource.getMessage("user.login_caracter", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getPassword())) {
			messagesReturn = messageSource.getMessage("user.password", null, LocaleContextHolder.getLocale());
		}else {
			
			user.setPassword(encodePassword(user, PrintVariables.SAVE));
			userDao.save(user);
		}
		
		return messagesReturn;
	}
	
	
	/*
	 * Método para la actualización de usuarios en un hotel
	 * @return String
	 */
	public String update(User user) throws MongoException, Exception {
		
		String messagesReturn = "";
	
		if(StringUtils.isBlank(user.getUuid())) {
			messagesReturn = messageSource.getMessage("user.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getHotelId())) {
			messagesReturn = messageSource.getMessage("user.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getRol())) {
			messagesReturn = messageSource.getMessage("user.rol", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getPersonId())) {
			messagesReturn = messageSource.getMessage("user.person", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getUser())) {
			messagesReturn = messageSource.getMessage("user.login", null, LocaleContextHolder.getLocale());
		}else if(validateUser(user.getHotelId(), user.getUser())) {
			messagesReturn = messageSource.getMessage("user.login_unique", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateSpecialCharacters(user.getUser())) {
			messagesReturn = messageSource.getMessage("user.login_caracter", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getPassword())) {
			messagesReturn = messageSource.getMessage("user.password", null, LocaleContextHolder.getLocale());
		}else {
			
			user.setPassword(encodePassword(user, PrintVariables.UPDATE));
			userDao.update(user);
		}
		
		return messagesReturn;
	}
	
	
	/*
	 * Método para listar todos los usuarios de un hotel
	 * @return List<User>
	 */
	public List<User> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<User> users = null;
		users = userDao.findByHotelId(hotelId);
		
		return users;
	}

	
	/*
	 * Método para buscar un usuario  de un hotel por Id
	 * @return User
	 */
	public User findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		User user = null;
		user = userDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return user;
	}

	
	/*
	 * Método para la validación del usuario y contraseña
	 * @return String
	 */
	public User findByUserAndPassword(String login, String password) throws MongoException, Exception {
		
		User user = new User();
		
		if(StringUtils.isBlank(login)) {
			user.setUuid(PrintVariables.VALIDACION);
		}else if(StringUtils.isBlank(password)) {
			user.setUuid(PrintVariables.VALIDACION);
		}else {
			
			encryptionData = new EncryptionData();
			password = encryptionData.encript(password);
			user = userDao.findByUserAndPassword(login, password);
		}
		
		return user;
	}
	
	
	/*
	 * Método para validar si un usuario ya se encuentra registrado
	 * @return String
	 */
	private Boolean validateUser(String hotelId, String login) throws MongoException, Exception {
		
		User user = null;
		user = userDao.findByUser(hotelId, login);
		
		if(user != null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/*
	 * Método para encriptar una contraseña
	 * @return String
	 */
	private String encodePassword(User user, String method) {
		
		String encodeReturn = "";
		String passwordOld = "";
		
		try {
			
			encryptionData = new EncryptionData();
			
			//Encripta la contraseña que se va a registrar
			if(method.equals(PrintVariables.SAVE)) {
				encodeReturn = encryptionData.encript(user.getPassword());
			}
			
			//Si va a actualizar valida la contraseña actual con la nueva, si son iguales, no hace nada de lo contrario encripta la nueva
			if(method.equals(PrintVariables.UPDATE)) {
				passwordOld = findByHotelIdAndUuid(user.getHotelId(), user.getUuid()).getPassword();
				
				if(!passwordOld.equals("")) {
					if(!passwordOld.equals(user.getPassword())) {
						encodeReturn = encryptionData.encript(user.getPassword());
					}else {
						encodeReturn = user.getPassword();
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			encodeReturn = user.getPassword();
		}
		
		return encodeReturn;
	}
}
