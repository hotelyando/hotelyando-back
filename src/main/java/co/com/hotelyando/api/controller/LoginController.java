package co.com.hotelyando.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.UsuarioBusiness;
import co.com.hotelyando.core.model.LoginRequest;
import co.com.hotelyando.core.model.LoginResponse;
import co.com.hotelyando.core.model.RespuestaServicio;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@Api(tags = "Login")
public class LoginController {
	
	
	private final UsuarioBusiness usuarioBusiness;
	private RespuestaServicio<LoginResponse> respuestaServicio = null;
	
	public LoginController(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
	}
	
	@PostMapping("/login")
	public ResponseEntity<RespuestaServicio<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
		
		respuestaServicio = usuarioBusiness.consultarUsuarioYContrasenia(loginRequest.getUsuario(), loginRequest.getContrasena());
			
		if(respuestaServicio != null) {
			return new ResponseEntity<RespuestaServicio<LoginResponse>>(respuestaServicio, HttpStatus.OK);
		}else {
			return new ResponseEntity<RespuestaServicio<LoginResponse>>(respuestaServicio, HttpStatus.PRECONDITION_REQUIRED);
		}
	}

}
