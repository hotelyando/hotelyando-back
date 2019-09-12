package co.com.hotelyando.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.services.IPersonaService;
import co.com.hotelyando.database.model.Persona;

@RestController
public class PersonaController {

	@Autowired
	private IPersonaService iPersonaService;
	
	@PostMapping("/persona")
	public ResponseEntity<String> registrarPersona(@RequestBody Persona persona){
		
		String retornoRespuesta = "";
		
		retornoRespuesta = iPersonaService.registrarPersona(persona); 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/persona/{tipoDocumento}/{numeroDocumento}")
	public ResponseEntity<Persona> consultarTipoYNumeroDocumento(@PathVariable String tipoDocumento, String numeroDocumento){
		
		Persona persona = null;
		
		persona = iPersonaService.consultarTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
		
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@GetMapping("/persona/{numeroDocumento}")
	public ResponseEntity<Persona> consultarNumeroDocumento(@PathVariable String numeroDocumento){
		
		Persona persona = null;
		
		persona = iPersonaService.consultarNumeroDocumento(numeroDocumento);
		
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}

	
}
