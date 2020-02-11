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

import co.com.hotelyando.core.business.PersonaBusiness;
import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Persona;
import co.com.hotelyando.database.model.Usuario;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Persona")
public class PersonaController {

	private final PersonaBusiness personaBusiness;
	
	private Utilidades utilidades;
	private Usuario usuario;
	
	public PersonaController(PersonaBusiness personaBusiness) {
		this.personaBusiness = personaBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/persona")
	public ResponseEntity<RespuestaServicio<Persona>> registrarPersona(@RequestBody Persona persona, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		
		RespuestaServicio<Persona> respuestaServicio = personaBusiness.registrarPersona(persona, usuario);
		
		if(respuestaServicio.getEstado().equals(ImpresionVariables.NEGOCIO)) {
			return new ResponseEntity<RespuestaServicio<Persona>>(respuestaServicio, HttpStatus.OK);
		}else if(respuestaServicio.getEstado().equals(ImpresionVariables.ERROR_TECNICO)){
			return new ResponseEntity<RespuestaServicio<Persona>>(respuestaServicio, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<RespuestaServicio<Persona>>(respuestaServicio, HttpStatus.OK);
		}
	}
	
	@GetMapping("/persona/{tipoDocumento}/{numeroDocumento}")
	public ResponseEntity<RespuestaServicio<Persona>> consultarTipoYNumeroDocumento(@PathVariable String tipoDocumento, @PathVariable String numeroDocumento, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		
		RespuestaServicio<Persona> respuestaServicio = personaBusiness.consultarTipoYNumeroDocumento(tipoDocumento, numeroDocumento, usuario);
		
		if(respuestaServicio.getEstado().equals(ImpresionVariables.NEGOCIO)) {
			return new ResponseEntity<RespuestaServicio<Persona>>(respuestaServicio, HttpStatus.OK);
		}else if(respuestaServicio.getEstado().equals(ImpresionVariables.ERROR_TECNICO)){
			return new ResponseEntity<RespuestaServicio<Persona>>(respuestaServicio, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<RespuestaServicio<Persona>>(respuestaServicio, HttpStatus.OK);
		}
	}
	
	@GetMapping("/persona/{numeroDocumento}")
	public ResponseEntity<RespuestaServicio<Persona>> consultarNumeroDocumento(@PathVariable String numeroDocumento, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		
		RespuestaServicio<Persona> respuestaServicio = personaBusiness.consultarNumeroDocumento(numeroDocumento, usuario);
		
		if(respuestaServicio.getEstado().equals(ImpresionVariables.NEGOCIO)) {
			return new ResponseEntity<RespuestaServicio<Persona>>(respuestaServicio, HttpStatus.OK);
		}else if(respuestaServicio.getEstado().equals(ImpresionVariables.ERROR_TECNICO)){
			return new ResponseEntity<RespuestaServicio<Persona>>(respuestaServicio, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<RespuestaServicio<Persona>>(respuestaServicio, HttpStatus.OK);
		}
	}
	
	
}
