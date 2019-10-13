package co.com.hotelyando.api.controller;

import java.util.List;
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

import co.com.hotelyando.core.business.PaqueteBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Paquete;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PaqueteController {
	
	private final PaqueteBusiness paqueteBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public PaqueteController(PaqueteBusiness paqueteBusiness) {
		this.paqueteBusiness = paqueteBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/paquete")
	public ResponseEntity<String> registrarPaquete(@RequestBody Paquete paquete, @RequestHeader Map<String, String> headers){
		
		String retornoRespuesta = "";
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			retornoRespuesta = paqueteBusiness.registrarPaquete(paquete, usuario);
		}catch (Exception e) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/paquete/{paqueteId}")
	public ResponseEntity<Paquete> consultarPaquetePorHotel(@PathVariable Integer paqueteId, @RequestHeader Map<String, String> headers){
		
		Paquete paquete = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			paquete = paqueteBusiness.consultarPaquetePorHotel(usuario, paqueteId);
			
		}catch (Exception e) {
			return new ResponseEntity<Paquete>(paquete, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<Paquete>(paquete, HttpStatus.OK);
		
	}
	
	@GetMapping("/paquete")
	public ResponseEntity<List<Paquete>> consultarPaquetesPorHotel(@RequestHeader Map<String, String> headers){
		
		List<Paquete> paquetes = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			paquetes = paqueteBusiness.consultarPaquetesPorHotel(usuario);
			
		}catch (Exception e) {
			return new ResponseEntity<List<Paquete>>(paquetes, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<List<Paquete>>(paquetes, HttpStatus.OK);
		
		
	}


}
