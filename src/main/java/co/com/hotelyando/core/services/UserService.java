package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.EncryptionData;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.UserDao;
import co.com.hotelyando.database.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private MessageSource messageSource;
	
	private RegularExpression regularExpression = null;
	private EncryptionData encryptionData = null;
	
	private User user;
	
	private final UserDao userDao;
	
	
	private String messageReturn = "";
	private String encodeReturn;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
		
		regularExpression = new RegularExpression();
		user = new User();
	}
	
	
	/*
	 * Método para el registro de usuario en un hotel
	 * @return String
	 */
	public String save(User user) throws MongoException, Exception {
		
		if(StringUtils.isBlank(user.getUuid())) {
			messageReturn = messageSource.getMessage("user.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getHotelId())) {
			messageReturn = messageSource.getMessage("user.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getRol())) {
			messageReturn = messageSource.getMessage("user.rol", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getPersonId())) {
			messageReturn = messageSource.getMessage("user.person", null, LocaleContextHolder.getLocale());
		}else if(findByHotelIdAndPersonId(user.getHotelId(), user.getPersonId()) != null) {
			messageReturn = messageSource.getMessage("user.person_unique", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getUser())) {
			messageReturn = messageSource.getMessage("user.login", null, LocaleContextHolder.getLocale());
		}else if(validateUser(user.getHotelId(), user.getUser(), false)) {
			messageReturn = messageSource.getMessage("user.login_unique", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateSpecialCharacters(user.getUser())) {
			messageReturn = messageSource.getMessage("user.login_caracter", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getPassword())) {
			messageReturn = messageSource.getMessage("user.password", null, LocaleContextHolder.getLocale());
		}else {
			
			user.setPassword(encodePassword(user, PrintVariable.SAVE));
			userDao.save(user);
		}
		
		return messageReturn;
	}
	
	
	/*
	 * Método para la actualización de usuarios en un hotel
	 * @return String
	 */
	public String update(User user) throws MongoException, Exception {
		
		if(StringUtils.isBlank(user.getUuid())) {
			messageReturn = messageSource.getMessage("user.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getHotelId())) {
			messageReturn = messageSource.getMessage("user.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getRol())) {
			messageReturn = messageSource.getMessage("user.rol", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getPersonId())) {
			messageReturn = messageSource.getMessage("user.person", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getUser())) {
			messageReturn = messageSource.getMessage("user.login", null, LocaleContextHolder.getLocale());
		}else if(validateUser(user.getHotelId(), user.getUser(), true)) {
			messageReturn = messageSource.getMessage("user.login_unique", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateSpecialCharacters(user.getUser())) {
			messageReturn = messageSource.getMessage("user.login_caracter", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(user.getPassword())) {
			messageReturn = messageSource.getMessage("user.password", null, LocaleContextHolder.getLocale());
		}else {
			
			user.setPassword(encodePassword(user, PrintVariable.UPDATE));
			userDao.update(user);
		}
		
		return messageReturn;
	}
	
	
	/*
	 * Método para listar todos los usuarios de un hotel
	 * @return List<User>
	 */
	public List<User> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<User> users = userDao.findByHotelId(hotelId);
		
		/*users.forEach((user) -> {
			user.setPassword("");
		});*/
		
		return users;
	}

	
	/*
	 * Método para buscar un usuario  de un hotel por Id
	 * @return User
	 */
	public User findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		User user = userDao.findByHotelIdAndUuid(hotelId, uuid);
		
		//user.setPassword("");
		
		return user;
	}

	
	/*
	 * Método para la validación del usuario y contraseña
	 * @return String
	 */
	public User findByUserAndPassword(String login, String password) throws MongoException, Exception {
		
		String passwordEncryption;
		
		if(StringUtils.isBlank(login)) {
			user.setUuid(PrintVariable.VALIDACION);
		}else if(StringUtils.isBlank(password)) {
			user.setUuid(PrintVariable.VALIDACION);
		}else {
			
			encryptionData = new EncryptionData();
			passwordEncryption = encryptionData.encript(password);
			user = userDao.findByUserAndPassword(login, passwordEncryption);
			
			if(user != null) {
				if(!user.isState()) {
					user.setUuid(PrintVariable.VALIDACION);
				}
			}
		}
		
		return user;
	}
	
	
	/*
	 * Método para validar si un usuario ya se encuentra registrado
	 * @return String
	 */
	private Boolean validateUser(String hotelId, String login, Boolean update) throws MongoException, Exception {
		
		User user = userDao.findByUser(hotelId, login);
		
		if (user == null) {
			return false;
		}else if(update && user.getUser().equals(login)) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	/*
	 * Método para encriptar una contraseña
	 * @return String
	 */
	private String encodePassword(User user, String method) {
		
		String passwordOld;
		
		try {
			
			encryptionData = new EncryptionData();
			
			//Encripta la contraseña que se va a registrar
			if(method.equals(PrintVariable.SAVE)) {
				encodeReturn = encryptionData.encript(user.getPassword());
			}
			
			//Si va a actualizar valida la contraseña actual con la nueva, si son iguales, no hace nada de lo contrario encripta la nueva
			if(method.equals(PrintVariable.UPDATE)) {
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
	
	
	public User findByHotelIdAndPersonId(String hotelId, String personId) throws MongoException, Exception {
		
		User user = userDao.findByHotelIdAndPersonId(hotelId, personId);
		
		return user;
		
	}
	
	
	public String delete(String uuid) throws MongoException, Exception {
		
		messageReturn = "";
		
		userDao.delete(uuid);
		
		return messageReturn;
		
	}
}
