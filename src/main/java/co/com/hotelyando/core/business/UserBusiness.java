package co.com.hotelyando.core.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class UserBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
	private final UserService userService;
	
	private ServiceResponse<User> serviceResponse;
	private ServiceResponses<User> serviceResponses;
	private ServiceResponse<LoginResponse> serviceLoginResponse;
	
	private String tokenHeader;
	
	private Utilities utilities;
	private Generic<User> generic;
	private Generic<LoginResponse> genericosLogin;
	
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
				serviceResponse = generic.messageReturn(user, PrintVariable.NEGOCIO, messageSource.getMessage("user.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				user.setUuid("");
				serviceResponse = generic.messageReturn(user, PrintVariable.VALIDACION, messageReturn);
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
	 * Método para la actualizaci�n de usuario en un hotel
	 * @return ServiceResponse<User>
	 */
	public ServiceResponse<User> update(User user, User user1) {
		
		String messageReturn = "";
		
		try {
			
			user.setHotelId(user1.getHotelId());
			
			messageReturn = userService.update(user);
			
			if(messageReturn.equals("")) {
				user.setPassword("");
				serviceResponse = generic.messageReturn(user, PrintVariable.NEGOCIO, messageSource.getMessage("user.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(user, PrintVariable.VALIDACION, messageReturn);
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
	 * Metodo que consultar� los usuarios por hotel, la informaci�n del hotel viene en el token, no hay necesidad de 
	 * enviarlo desde el servicio.
	 * @return ServiceResponse<User>
	 */
	public ServiceResponses<User> findByHotelId(User user) {
		
		try {
			
			List<User> users = userService.findByHotelId(user.getHotelId());
			
			if(users != null) {
				serviceResponses = generic.messagesReturn(users, PrintVariable.NEGOCIO, messageSource.getMessage("user.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(users, PrintVariable.VALIDACION, messageSource.getMessage("user.not_content", null, LocaleContextHolder.getLocale()));
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
	 * Metodo que se encarga de validar el usuario y contrase�a ingresado, en caso de ser correcto, generar� un token con 
	 * la informaci�n del usuario para poder navegar con los dem�s servicios por medio del token generado, este token dar�
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
				
				//Obtenemos el token, en donde se encuentra la informaci�n del usuario logueado
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
				serviceLoginResponse = genericosLogin.messageReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("user.use_not_found", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceLoginResponse = genericosLogin.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceLoginResponse = genericosLogin.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
				
		return serviceLoginResponse;
	}
	
	
	public ServiceResponse<LoginResponse> logout(Map<String, String> headers, String nombreEncabezado){
		
		genericosLogin = new Generic<LoginResponse>();
		
		try {
			
			headers.forEach((key,value) ->{
				
				if(key.equals(nombreEncabezado)) {
					tokenHeader = value.toString();
				}
			});
			
			String jwtToken;
			
			jwtToken = tokenHeader.replace(PrintVariable.PREFIX, "");
			
			Claims claims = Jwts.parser().setSigningKey(PrintVariable.SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
			claims.setExpiration(new Date(System.currentTimeMillis() + 1000));
			
			serviceLoginResponse = genericosLogin.messageReturn(null, PrintVariable.NEGOCIO, "Vuelva pronto!");
			
		}catch (Exception e) {
			e.printStackTrace();
			serviceLoginResponse = genericosLogin.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
		}
		
				
		return serviceLoginResponse;
	}
	
	
	/*
	 * Método para la actualizaci�n de la contrase�a de un usuario de un hotel
	 * @return ServiceResponse<User>
	 */
	//public ServiceResponse<User> changePassword(User user, String oldPassword, String newPassword) {
	public ServiceResponse<User> changePassword(User user, String json) {
		
		String messageReturn = "";
		String oldPassword = "";
		String newPassword = "";
		
		try {
			
			JsonElement jsonElement = new JsonParser().parse(json);
			JsonObject jsonObject = jsonElement.getAsJsonObject(); 		
			
			oldPassword = jsonObject.get("oldPassword").getAsString();
			newPassword = jsonObject.get("newPassword").getAsString();
			
			messageReturn = userService.changePassword(user, oldPassword, newPassword);
			
			if(messageReturn.equals("")) {
				user.setPassword("");
				serviceResponse = generic.messageReturn(user, PrintVariable.NEGOCIO, messageSource.getMessage("user.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(user, PrintVariable.VALIDACION, messageReturn);
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
	 * Método que envie correo al usurio para recuperar contrase�a
	 * @return ServiceResponse<User>
	 */
	public ServiceResponse<User> recoveryPassword(String json) {
		
		String messageReturn = "";
		String login = "";
		try {
			
			JsonElement jsonElement = new JsonParser().parse(json);
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			
			login = jsonObject.get("login").getAsString();
			
			//messageReturn = userService.recoveryPassword(hotelId, login);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, "Se ha enviado un correo electrónico a " + login);
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
	 * Método para la eliminación de usuario en un hotel
	 * @return ServiceResponse<User>
	 */
	public ServiceResponse<User> delete(String uuid, User user) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = userService.delete(uuid);
			
			if(messageReturn.equals("")) {
				user.setPassword("");
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("user.delete_ok", null, LocaleContextHolder.getLocale()));
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


}
