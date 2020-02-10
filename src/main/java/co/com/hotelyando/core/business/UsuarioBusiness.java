package co.com.hotelyando.core.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.LoginResponse;
import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.model.RespuestasServicio;
import co.com.hotelyando.core.services.UsuarioService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.core.utilities.ImpresionEntidades;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.JwtToken;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.model.Usuario;

@Service
public class UsuarioBusiness {
	

	private final UsuarioService usuarioService;
	private RespuestaServicio<Usuario> respuestaServicio;
	private RespuestasServicio<Usuario> respuestasServicio;
	private Utilidades utilidades = null;
	private Genericos<Usuario> genericos = null;

	public UsuarioBusiness(UsuarioService usuarioService) {
		
		this.usuarioService = usuarioService;
		respuestaServicio = new RespuestaServicio<Usuario>();
		respuestasServicio = new RespuestasServicio<Usuario>();
		utilidades = new Utilidades();
		genericos = new Genericos<Usuario>();
	}
	
	/*
	 * Registrar usuario
	 */
	public RespuestaServicio<Usuario> registrarUsuario(Usuario usuario, Usuario usuario1) {
		
		String retornoMensaje = "";
		
		try {
			
			usuario.setHotelId(usuario1.getHotelId());
			usuario.setUsuarioId(utilidades.generadorId());
			
			retornoMensaje = usuarioService.registrarUsuario(usuario);
			
			if(retornoMensaje.equals("")) {
				respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.NEGOCIO, ImpresionEntidades.USUARIO_REGISTRADO);
			}else {
				respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.ADVERTENCIA, retornoMensaje);
			}
			
		}catch (Exception e) {
			respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return respuestaServicio;
	}

	/*
	 * Metodo que consultará los usuarios por hotel, la información del hotel viene en el token, no hay necesidad de 
	 * enviarlo desde el servicio.
	 */
	public RespuestasServicio<Usuario> consultarUsuariosPorHotel(Usuario usuario) {
		
		List<Usuario> usuarios = null;
		
		try {
			
			usuarios = usuarioService.consultarUsuariosPorHotel(usuario.getHotelId());
			
			if(usuarios != null) {
				respuestasServicio = genericos.retornoMensajes(usuarios, ImpresionVariables.NEGOCIO, ImpresionEntidades.DATOS_RETORNADOS + usuarios.size());
			}else {
				respuestasServicio = genericos.retornoMensajes(usuarios, ImpresionVariables.ADVERTENCIA, ImpresionEntidades.DATOS_RETORNADOS + 0);
			}
			
		}catch (Exception e) {
			respuestasServicio = genericos.retornoMensajes(usuarios, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return respuestasServicio;
	}

	/*
	 * Consultar usuario por hotel
	 */
	public RespuestaServicio<Usuario> consultarUsuarioPorHotel(Usuario usuario, String usuarioId) {
		
		Usuario usuario1 = null;
		
		try {
			
			usuario1 = usuarioService.consultarUsuarioPorHotel(usuario.getHotelId(), usuarioId);
			
			if(usuario1 != null) {
				respuestaServicio = genericos.retornoMensaje(usuario1, ImpresionVariables.NEGOCIO, ImpresionEntidades.DATOS_RETORNADOS);
			}else {
				respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.ADVERTENCIA, ImpresionEntidades.USUARIO_NO_ENCONTRADO);
			}
			
			
		}catch (Exception e) {
			respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return respuestaServicio;
	}
	
	/*
	 * Metodo que se encarga de validar el usuario y contraseña ingresado, en caso de ser correcto, generará un token con 
	 * la información del usuario para poder navegar con los demás servicios por medio del token generado, este token dará
	 * los permisos necesarios a los servicios que este permitido consumir.
	 */
	public RespuestaServicio<LoginResponse> consultarUsuarioYContrasenia(String login, String contrasenia){
		
		JwtToken jwtToken;
		String token;
		
		RespuestaServicio<LoginResponse> respuestaServicio = null;
		Hotel hotel = null;
		LoginResponse loginResponse = null;
		Usuario usuario = null;
		Genericos<LoginResponse> genericosLogin = null;
		try {
			
			usuario = usuarioService.consultarUsuarioYContrasenia(login, contrasenia);
			
			genericosLogin = new Genericos<LoginResponse>();
			
			if(usuario != null && !usuario.getUsuarioId().equals(ImpresionVariables.VALIDACION)){
				
				//Obtenemos el token, en donde se encuentra la información del usuario logueado
				jwtToken = new JwtToken();
				token = jwtToken.getJWTToken(usuario);
				
				hotel = new Hotel();
				hotel.setHotelId(usuario.getHotelId());
				
				List<Hotel> hotels = new ArrayList<Hotel>();
				hotels.add(hotel);
				
				loginResponse = new LoginResponse();
				loginResponse.setToken(token);
				loginResponse.setUser(usuario.getUsuario());
				loginResponse.setHotels(hotels);
				
				respuestaServicio = genericosLogin.retornoMensaje(loginResponse, ImpresionVariables.NEGOCIO, ImpresionEntidades.USUARIO_LOGUEADO);
				
			}else {
				respuestaServicio = genericosLogin.retornoMensaje(null, ImpresionVariables.ADVERTENCIA, ImpresionEntidades.USUARIO_NO_ENCONTRADO);
			}
			
		}catch (Exception e) {
			respuestaServicio = genericosLogin.retornoMensaje(null, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
				
		return respuestaServicio;
	}


}
