package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.UsuarioBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Usuario;

@RestController
public class UsuarioController {
	
	private final UsuarioBusiness usuarioBusiness;
	
	private Utilidades utilidades;
	private String usuarioJson;
	
	public UsuarioController(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario, @RequestHeader Map<String, String> headers){
		
		String retornoRespuesta = "";
		
		try {
			
			//usuarioJson = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			retornoRespuesta = usuarioBusiness.registrarUsuario(usuario, null);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<Usuario> consultarUsuarioPorHotel(@PathVariable Integer usuarioId, @RequestHeader Map<String, String> headers){
		
		Usuario usuario1 = null;
		
		try {
			
			usuarioJson = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			usuario1 = usuarioBusiness.consultarUsuarioPorHotel(usuarioJson, usuarioId);
			
		}catch (Exception e) {
			return new ResponseEntity<Usuario>(usuario1, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<Usuario>(usuario1, HttpStatus.OK);
		
	}
	
	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> consultarUsuariosPorHotel(@RequestHeader Map<String, String> headers){
		
		List<Usuario> usuarios = null;
		
		try {
			
			usuarioJson = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			usuarios = usuarioBusiness.consultarUsuariosPorHotel(usuarioJson);
			
		}catch (Exception e) {
			return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
		
		
	}
}
