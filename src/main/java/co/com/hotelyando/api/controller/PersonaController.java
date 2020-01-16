package co.com.hotelyando.api.controller;

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

import co.com.hotelyando.core.business.PersonaBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Persona;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PersonaController {

	private final PersonaBusiness personaBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public PersonaController(PersonaBusiness personaBusiness) {
		this.personaBusiness = personaBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/persona")
	public ResponseEntity<String> registrarPersona(@RequestBody Persona persona, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		
		String retornoRespuesta = personaBusiness.registrarPersona(persona, usuario);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		} 
	}
	
	@GetMapping("/persona/{tipoDocumento}/{numeroDocumento}")
	public ResponseEntity<Persona> consultarTipoYNumeroDocumento(@PathVariable String tipoDocumento, @PathVariable String numeroDocumento, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		
		Persona	persona = personaBusiness.consultarTipoYNumeroDocumento(tipoDocumento, numeroDocumento, usuario);
		
		if(persona == null) {
			return new ResponseEntity<Persona>(persona, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		}
	}
	
	@GetMapping("/persona/{numeroDocumento}")
	public ResponseEntity<Persona> consultarNumeroDocumento(@PathVariable String numeroDocumento, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		
		Persona persona = personaBusiness.consultarNumeroDocumento(numeroDocumento, usuario);
		
		if(persona == null) {
			return new ResponseEntity<Persona>(persona, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		}
	}
	
	
}
