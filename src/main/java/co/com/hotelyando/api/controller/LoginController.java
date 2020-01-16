package co.com.hotelyando.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.UsuarioBusiness;
import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.core.utilities.JwtToken;
import co.com.hotelyando.database.model.Usuario;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class LoginController {
	
	
	private final UsuarioBusiness usuarioBusiness;
	private Genericos<Usuario> genericos;
	
	public LoginController(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
		genericos = new Genericos<Usuario>();
	}
	
	@PostMapping("/login")
	public ResponseEntity<RespuestaServicio<Usuario>> login(@RequestHeader String customerId, @RequestHeader String password){
		
		JwtToken jwtToken;
		String token;
		HttpHeaders httpHeaders;
		
		RespuestaServicio<Usuario> respuestaServicio = null;
		Usuario usuario = null;
		List<String> lista = null;
		
		
		try {//encritpacion
			usuario = usuarioBusiness.consultarUsuarioYContrasenia(customerId, password);
			
			if(usuario != null){
				
				jwtToken = new JwtToken();
				token = jwtToken.getJWTToken(usuario);
				
				httpHeaders = new HttpHeaders();
				
				lista = new ArrayList<String>();
				lista.add(token);
				
				respuestaServicio = genericos.retornoMensaje(usuario, "0", lista); 
				
				return new ResponseEntity<>(respuestaServicio, httpHeaders, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(respuestaServicio, HttpStatus.PRECONDITION_REQUIRED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(respuestaServicio, HttpStatus.NOT_IMPLEMENTED);
		}
		
	}

}
