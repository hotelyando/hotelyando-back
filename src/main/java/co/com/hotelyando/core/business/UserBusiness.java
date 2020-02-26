package co.com.hotelyando.core.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.LoginResponse;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.UserService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.JwtToken;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.model.User;

@Service
public class UserBusiness {
	

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
	 * Registrar usuario
	 */
	public ServiceResponse<User> save(User user, User user1) {
		
		String messageReturn = "";
		
		try {
			
			user.setHotelId(user1.getHotelId());
			user.setUuid(utilities.generadorId());
			
			messageReturn = userService.save(user);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, PrintEntity.USUARIO_REGISTRADO);
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, messageReturn);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}

	/*
	 * Metodo que consultará los usuarios por hotel, la información del hotel viene en el token, no hay necesidad de 
	 * enviarlo desde el servicio.
	 */
	public ServiceResponses<User> findByHotelId(User user) {
		
		List<User> users = null;
		
		try {
			
			users = userService.findByHotelId(user.getHotelId());
			
			if(users != null) {
				serviceResponses = generic.messagesReturn(users, PrintVariables.NEGOCIO, PrintEntity.DATOS_RETORNADOS + users.size());
			}else {
				serviceResponses = generic.messagesReturn(users, PrintVariables.ADVERTENCIA, PrintEntity.DATOS_RETORNADOS + 0);
			}
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(users, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}

	/*
	 * Consultar usuario por hotel
	 */
	public ServiceResponse<User> findByHotelIdAndUuid(User user, String uuid) {
		
		User user1 = null;
		
		try {
			
			user1 = userService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(user1 != null) {
				serviceResponse = generic.messageReturn(user1, PrintVariables.NEGOCIO, PrintEntity.DATOS_RETORNADOS);
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, PrintEntity.USUARIO_NO_ENCONTRADO);
			}
			
			
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
				
				serviceResponse = genericosLogin.messageReturn(loginResponse, PrintVariables.NEGOCIO, PrintEntity.USUARIO_LOGUEADO);
				
			}else {
				serviceResponse = genericosLogin.messageReturn(null, PrintVariables.ADVERTENCIA, PrintEntity.USUARIO_NO_ENCONTRADO);
			}
			
		}catch (Exception e) {
			serviceResponse = genericosLogin.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
				
		return serviceResponse;
	}


}
