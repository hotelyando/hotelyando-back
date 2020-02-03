package co.com.hotelyando.core.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.LoginResponse;
import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.services.UsuarioService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.core.utilities.JwtToken;
import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.model.Usuario;

@Service
public class UsuarioBusiness {

private final UsuarioService usuarioService;
	
	public UsuarioBusiness(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public String registrarUsuario(Usuario usuario, Usuario usuario1) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = usuarioService.registrarUsuario(usuario);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Usuario> consultarUsuariosPorHotel(Usuario usuario) {
		
		List<Usuario> usuarios = null;
		
		try {
			
			usuarios = usuarioService.consultarUsuariosPorHotel(usuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return usuarios;
	}

	public Usuario consultarUsuarioPorHotel(Usuario usuario, Integer usuarioId) {
		
		Usuario usuario1 = null;
		
		try {
			
			usuario1 = usuarioService.consultarUsuarioPorHotel(usuario.getHotelId(), usuarioId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario1;
	}
	
	public RespuestaServicio<LoginResponse> consultarUsuarioYContrasenia(String login, String contrasenia){
		
		Genericos<LoginResponse> genericos;
		JwtToken jwtToken;
		String token;
		
		RespuestaServicio<LoginResponse> respuestaServicio = null;
		
		LoginResponse loginResponse = null;
		List<String> lista = null;
		
		Usuario usuario = null;
		
		try {
			
			usuario = usuarioService.consultarUsuarioYContrasenia(login, contrasenia);
			
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
				
				genericos = new Genericos<LoginResponse>();
				respuestaServicio = genericos.retornoMensaje(loginResponse, "0", lista); 
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		return respuestaServicio;
	}


}
