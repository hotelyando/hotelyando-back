package co.com.hotelyando.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.UsuarioBusiness;
import co.com.hotelyando.core.model.LoginRequest;
import co.com.hotelyando.core.model.LoginResponse;
import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.core.utilities.JwtToken;
import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.model.Usuario;

@RestController
public class LoginController {
	
	
	private final UsuarioBusiness usuarioBusiness;
	private Genericos<LoginResponse> genericos;
	
	public LoginController(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
		genericos = new Genericos<LoginResponse>();
	}
	
	@PostMapping("/login")
	public ResponseEntity<RespuestaServicio<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
		
		JwtToken jwtToken;
		String token;
		
		RespuestaServicio<LoginResponse> respuestaServicio = null;
		Usuario usuario = null;
		LoginResponse loginResponse = null;
		List<String> lista = null;
		
		
		String customerId;
		String password;
		
		try {//encritpacion
			
			customerId = loginRequest.getUsuario();
			password = loginRequest.getContrasena();
			
			usuario = usuarioBusiness.consultarUsuarioYContrasenia(customerId, password);
			
			if(usuario != null){
				
				jwtToken = new JwtToken();
				token = jwtToken.getJWTToken(usuario);
				
				Hotel hotel = new Hotel();
				hotel.setHotelId(usuario.getHotelId());
				
				List<Hotel> hotels = new ArrayList<Hotel>();
				hotels.add(hotel);
				
				loginResponse = new LoginResponse();
				loginResponse.setToken(token);
				loginResponse.setUser(usuario.getUsuario());
				loginResponse.setHotels(hotels);
				
				lista = new ArrayList<String>();
				lista.add("1");
				
				respuestaServicio = genericos.retornoMensaje(loginResponse, "0", lista); 
				
				return new ResponseEntity<RespuestaServicio<LoginResponse>>(respuestaServicio, HttpStatus.OK);
			}
			
			return new ResponseEntity<RespuestaServicio<LoginResponse>>(respuestaServicio, HttpStatus.PRECONDITION_REQUIRED);
			
		} catch (Exception e) {
			return new ResponseEntity<RespuestaServicio<LoginResponse>>(respuestaServicio, HttpStatus.NOT_IMPLEMENTED);
		}
		
	}

}
