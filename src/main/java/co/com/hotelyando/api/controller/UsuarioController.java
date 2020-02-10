package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.UsuarioBusiness;
import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.model.RespuestasServicio;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Usuario;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Usuario")
public class UsuarioController {
	
	private final UsuarioBusiness usuarioBusiness;
	
	private Utilidades utilidades;
	private Usuario usuarioJson;
	
	public UsuarioController(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<RespuestaServicio<Usuario>> registrarUsuario(@RequestBody Usuario usuario, @RequestHeader Map<String, String> headers){
		
		usuarioJson = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		RespuestaServicio<Usuario> retornoRespuesta = usuarioBusiness.registrarUsuario(usuario, usuarioJson);
		
		if(retornoRespuesta.getEstado().equals("0")) {
			return new ResponseEntity<RespuestaServicio<Usuario>>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RespuestaServicio<Usuario>>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<RespuestaServicio<Usuario>> consultarUsuarioPorHotel(@PathVariable String usuarioId, @RequestHeader Map<String, String> headers){
		
		usuarioJson = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		RespuestaServicio<Usuario> respuestaServicio = usuarioBusiness.consultarUsuarioPorHotel(usuarioJson, usuarioId);
		
		if(respuestaServicio == null) {
			return new ResponseEntity<RespuestaServicio<Usuario>>(respuestaServicio, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<RespuestaServicio<Usuario>>(respuestaServicio, HttpStatus.OK);
		}
	}
	
	@GetMapping("/usuario")
	public ResponseEntity<RespuestasServicio<Usuario>> consultarUsuariosPorHotel(@RequestHeader Map<String, String> headers){
		
		usuarioJson = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		RespuestasServicio<Usuario> respuestaServicio = usuarioBusiness.consultarUsuariosPorHotel(usuarioJson);
			
		if(respuestaServicio == null) {
			return new ResponseEntity<RespuestasServicio<Usuario>>(respuestaServicio, HttpStatus.NOT_IMPLEMENTED);
		}else {
			return new ResponseEntity<RespuestasServicio<Usuario>>(respuestaServicio, HttpStatus.OK);
		}
	}
}
