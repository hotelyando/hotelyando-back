package co.com.hotelyando.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.UsuarioBusiness;
import co.com.hotelyando.core.model.LoginRequest;
import co.com.hotelyando.core.model.ResponseService;
import co.com.hotelyando.core.model.Status;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.JwtToken;
import co.com.hotelyando.database.model.Usuario;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class LoginController {
	
	private final UsuarioBusiness usuarioBusiness;
	
	public LoginController(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseService> login(@RequestBody LoginRequest loginRequest){
		
		JwtToken jwtToken;
		String token;
		HttpHeaders httpHeaders;
		
		ResponseService responseService = null;
		Status status = null;
		Usuario usuario = null;
		
		String customerId;
		String password;
		
		try {//encritpacion
			
			customerId = loginRequest.getUsuario();
			password = loginRequest.getContrasena();
			
			usuario = usuarioBusiness.consultarUsuarioYContrasenia(customerId, password);
			
			if(usuario != null){
				
				jwtToken = new JwtToken();
				token = jwtToken.getJWTToken(usuario);
				
				httpHeaders = new HttpHeaders();
				httpHeaders.set(ImpresionVariables.HEADER, token);
				
				status = new Status();
				status.setStatus_code(200);
				status.setStatus_desc("");
				
				responseService = new ResponseService();
				responseService.setUsuario(usuario);
				responseService.setResponse(usuario.getPersona().getNombreCompleto());
				responseService.setStatus(status);
				
				return new ResponseEntity<>(responseService, httpHeaders, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(responseService, HttpStatus.PRECONDITION_REQUIRED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(responseService, HttpStatus.NOT_IMPLEMENTED);
		}
		
	}

}
