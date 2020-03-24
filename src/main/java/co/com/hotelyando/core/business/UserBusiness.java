package co.com.hotelyando.core.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.authorization.JwtToken;
import co.com.hotelyando.core.model.LoginResponse;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.UserService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.model.User;

@Service
public class UserBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
	private final UserService userService;
	
	private ServiceResponse<User> serviceResponse;
	private ServiceResponses<User> serviceResponses;
	private ServiceResponse<LoginResponse> serviceLoginResponse;
	
	
	private Utilities utilities;
	private Generic<User> generic;
	private Generic<LoginResponse> genericosLogin;
	

	private String messageReturn;
	
	public UserBusiness(UserService userService) {
		
		this.userService = userService;
		serviceResponse = new ServiceResponse<User>();
		serviceResponses = new ServiceResponses<User>();
		utilities = new Utilities();
		generic = new Generic<User>();
	}
	
	
	/*
	 * Método para el registro de usuario en un hotel
	 * @return ServiceResponse<User>
	 */
	public ServiceResponse<User> save(User user, User user1) {
		
		try {
			
			user.setHotelId(user1.getHotelId());
			user.setUuid(utilities.generadorId());
			
			messageReturn = userService.save(user);
			
			if(messageReturn.equals("")) {
				user.setPassword("");
				serviceResponse = generic.messageReturn(user, PrintVariable.NEGOCIO, messageSource.getMessage("user.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	/*
	 * Método para la actualización de usuario en un hotel
	 * @return ServiceResponse<User>
	 */
	public ServiceResponse<User> update(User user, User user1) {
		
		try {
			
			user.setHotelId(user1.getHotelId());
			
			messageReturn = userService.update(user);
			
			if(messageReturn.equals("")) {
				user.setPassword("");
				serviceResponse = generic.messageReturn(user, PrintVariable.NEGOCIO, messageSource.getMessage("user.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

	
	/*
	 * Metodo que consultará los usuarios por hotel, la información del hotel viene en el token, no hay necesidad de 
	 * enviarlo desde el servicio.
	 * @return ServiceResponse<User>
	 */
	public ServiceResponses<User> findByHotelId(User user) {
		
		try {
			
			List<User> users = userService.findByHotelId(user.getHotelId());
			
			if(users != null) {
				serviceResponses = generic.messagesReturn(users, PrintVariable.NEGOCIO, messageSource.getMessage("user.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(users, PrintVariable.ADVERTENCIA, messageSource.getMessage("user.not_content", null, LocaleContextHolder.getLocale()));
			}

		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}

	
	/*
	 * Método para la consulta de un usuario por hotel
	 * @return ServiceResponse<User>
	 */
	public ServiceResponse<User> findByHotelIdAndUuid(User user, String uuid) {
		
		try {
			
			User user1 = userService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(user1 != null) {
				serviceResponse = generic.messageReturn(user1, PrintVariable.NEGOCIO, messageSource.getMessage("user.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("user.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	/*
	 * Metodo que se encarga de validar el usuario y contraseña ingresado, en caso de ser correcto, generará un token con 
	 * la información del usuario para poder navegar con los demás servicios por medio del token generado, este token dará
	 * los permisos necesarios a los servicios que este permitido consumir.
	 * @return ServiceResponse<LoginReponse>
	 */
	public ServiceResponse<LoginResponse> findByUserAndPassword(String login, String password){
		
		JwtToken jwtToken;
		
		String token;
		
		try {
			
			User user = userService.findByUserAndPassword(login, password);
			
			genericosLogin = new Generic<LoginResponse>();
			
			if(user != null && !user.getUuid().equals(PrintVariable.VALIDACION)){
				
				//Obtenemos el token, en donde se encuentra la información del usuario logueado
				jwtToken = new JwtToken();
				token = jwtToken.getJWTToken(user);
				
				Hotel hotel = new Hotel();
				hotel.setUuid(user.getHotelId());
				
				List<Hotel> hotels = new ArrayList<Hotel>();
				hotels.add(hotel);
				
				LoginResponse loginResponse = new LoginResponse();
				loginResponse.setToken(token);
				loginResponse.setUser(user.getUser());
				loginResponse.setHotels(hotels);
				
				serviceLoginResponse = genericosLogin.messageReturn(loginResponse, PrintVariable.NEGOCIO, messageSource.getMessage("user.use_found", null, LocaleContextHolder.getLocale()));
				
			}else {
				serviceLoginResponse = genericosLogin.messageReturn(null, PrintVariable.ADVERTENCIA, messageSource.getMessage("user.use_not_found", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceLoginResponse = genericosLogin.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceLoginResponse = genericosLogin.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
				
		return serviceLoginResponse;
	}


}
