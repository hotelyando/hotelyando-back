package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.PersonaBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Persona;

@RestController
public class PersonaController {

	private PersonaBusiness personaBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public PersonaController(PersonaBusiness personaBusiness) {
		this.personaBusiness = personaBusiness;
	}
	
	public PersonaController() {
		utilidades = new Utilidades();
	}
	
	@PostMapping("/persona")
	public ResponseEntity<String> registrarPersona(@RequestBody Persona persona, @RequestHeader Map<String, String> headers){
		
		String retornoRespuesta = "";
		
		try {
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			retornoRespuesta = personaBusiness.registrarPersona(persona, usuario);
		} catch (Exception e) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NOT_IMPLEMENTED);
		} 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/persona/{tipoDocumento}/{numeroDocumento}")
	public ResponseEntity<Persona> consultarTipoYNumeroDocumento(@PathVariable String tipoDocumento, @PathVariable String numeroDocumento, @RequestHeader Map<String, String> headers){
		
		Persona persona = null;
		
		try {
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			persona = personaBusiness.consultarTipoYNumeroDocumento(tipoDocumento, numeroDocumento, usuario);
		} catch (Exception e) {
			return new ResponseEntity<Persona>(persona, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@GetMapping("/persona/{numeroDocumento}")
	public ResponseEntity<Persona> consultarNumeroDocumento(@PathVariable String numeroDocumento, @RequestHeader Map<String, String> headers){
		
		Persona persona = null;
		
		try {
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			persona = personaBusiness.consultarNumeroDocumento(numeroDocumento, usuario);
		}catch (Exception e) {
			return new ResponseEntity<Persona>(persona, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		
	}
	
	
}
