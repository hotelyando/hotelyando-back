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
import co.com.hotelyando.core.utilities.PrintVariables;
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
	private Utilities utilities = null;
	private Generic<User> generic = null;

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
		
		String messageReturn = "";
		
		try {
			
			user.setHotelId(user1.getHotelId());
			user.setUuid(utilities.generadorId());
			
			messageReturn = userService.save(user);
			
			if(messageReturn.equals("")) {
				user.setPassword("");
				serviceResponse = generic.messageReturn(user, PrintVariables.NEGOCIO, messageSource.getMessage("user.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	/*
	 * Método para la actualización de usuario en un hotel
	 * @return ServiceResponse<User>
	 */
	public ServiceResponse<User> update(User user, User user1) {
		
		String messageReturn = "";
		
		try {
			
			user.setHotelId(user1.getHotelId());
			
			messageReturn = userService.update(user);
			
			if(messageReturn.equals("")) {
				user.setPassword("");
				serviceResponse = generic.messageReturn(user, PrintVariables.NEGOCIO, messageSource.getMessage("user.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
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
		
		List<User> users = null;
		
		try {
			
			users = userService.findByHotelId(user.getHotelId());
			
			if(users != null) {
				serviceResponses = generic.messagesReturn(users, PrintVariables.NEGOCIO, messageSource.getMessage("user.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(users, PrintVariables.ADVERTENCIA, messageSource.getMessage("user.not_content", null, LocaleContextHolder.getLocale()));
			}

		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}

	
	/*
	 * Método para la consulta de un usuario por hotel
	 * @return ServiceResponse<User>
	 */
	public ServiceResponse<User> findByHotelIdAndUuid(User user, String uuid) {
		
		User user1 = null;
		
		try {
			
			user1 = userService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(user1 != null) {
				serviceResponse = generic.messageReturn(user1, PrintVariables.NEGOCIO, messageSource.getMessage("user.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.VALIDACION, messageSource.getMessage("user.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
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
		
		ServiceResponse<LoginResponse> serviceResponse = null;
		Generic<LoginResponse> genericosLogin = null;
		
		Hotel hotel = null;
		LoginResponse loginResponse = null;
		User user = null;
		
		
		try {
			
			user = userService.findByUserAndPassword(login, password);
			
			genericosLogin = new Generic<LoginResponse>();
			
			if(user != null && !user.getUuid().equals(PrintVariables.VALIDACION)){
				
				//Obtenemos el token, en donde se encuentra la información del usuario logueado
				jwtToken = new JwtToken();
				token = jwtToken.getJWTToken(user);
				
				hotel = new Hotel();
				hotel.setUuid(user.getHotelId());
				
				List<Hotel> hotels = new ArrayList<Hotel>();
				hotels.add(hotel);
				
				loginResponse = new LoginResponse();
				loginResponse.setToken(token);
				loginResponse.setUser(user.getUser());
				loginResponse.setHotels(hotels);
				
				serviceResponse = genericosLogin.messageReturn(loginResponse, PrintVariables.NEGOCIO, messageSource.getMessage("user.use_found", null, LocaleContextHolder.getLocale()));
				
			}else {
				serviceResponse = genericosLogin.messageReturn(null, PrintVariables.ADVERTENCIA, messageSource.getMessage("user.use_not_found", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = genericosLogin.messageReturn(null, PrintVariables.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = genericosLogin.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
				
		return serviceResponse;
	}


}
