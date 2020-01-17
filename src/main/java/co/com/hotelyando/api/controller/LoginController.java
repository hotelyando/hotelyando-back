package co.com.hotelyando.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.UsuarioBusiness;
import co.com.hotelyando.core.model.LoginRequest;
import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.core.utilities.JwtToken;
import co.com.hotelyando.database.model.Usuario;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
public class LoginController {
	
	
	private final UsuarioBusiness usuarioBusiness;
	private Genericos<Usuario> genericos;
	
	public LoginController(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
		genericos = new Genericos<Usuario>();
	}
	
	@PostMapping("/login")
	public ResponseEntity<RespuestaServicio<Usuario>> login(@RequestBody LoginRequest loginRequest){
		
		JwtToken jwtToken;
		String token;
		
		RespuestaServicio<Usuario> respuestaServicio = null;
		Usuario usuario = null;
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
				
				lista = new ArrayList<String>();
				lista.add(token);
				
				respuestaServicio = genericos.retornoMensaje(usuario, "0", lista); 
				
				return new ResponseEntity<RespuestaServicio<Usuario>>(respuestaServicio, HttpStatus.OK);
			}
			
			return new ResponseEntity<RespuestaServicio<Usuario>>(respuestaServicio, HttpStatus.PRECONDITION_REQUIRED);
			
		} catch (Exception e) {
			return new ResponseEntity<RespuestaServicio<Usuario>>(respuestaServicio, HttpStatus.NOT_IMPLEMENTED);
		}
		
	}

}
