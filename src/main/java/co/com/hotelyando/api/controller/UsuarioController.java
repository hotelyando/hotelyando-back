package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Usuario;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UsuarioController {
	
	private final UsuarioBusiness usuarioBusiness;
	
	private Utilidades utilidades;
	private Usuario usuarioJson;
	
	public UsuarioController(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario, @RequestHeader Map<String, String> headers){
		
		usuarioJson = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		String retornoRespuesta = usuarioBusiness.registrarUsuario(usuario, usuarioJson);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<Usuario> consultarUsuarioPorHotel(@PathVariable Integer usuarioId, @RequestHeader Map<String, String> headers){
		
		usuarioJson = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		Usuario	usuario1 = usuarioBusiness.consultarUsuarioPorHotel(usuarioJson, usuarioId);
		
		if(usuario1 == null) {
			return new ResponseEntity<Usuario>(usuario1, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Usuario>(usuario1, HttpStatus.OK);
		}
	}
	
	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> consultarUsuariosPorHotel(@RequestHeader Map<String, String> headers){
		
		usuarioJson = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		List<Usuario> usuarios = usuarioBusiness.consultarUsuariosPorHotel(usuarioJson);
			
		if(usuarios == null) {
			return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.NOT_IMPLEMENTED);
		}else {
			return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
		}
	}
}
