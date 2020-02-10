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

import co.com.hotelyando.core.business.PaqueteBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Paquete;
import co.com.hotelyando.database.model.Usuario;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Paquete")
public class PaqueteController {
	
	private final PaqueteBusiness paqueteBusiness;
	
	private Utilidades utilidades;
	private Usuario usuario;
	
	public PaqueteController(PaqueteBusiness paqueteBusiness) {
		this.paqueteBusiness = paqueteBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/paquete")
	public ResponseEntity<String> registrarPaquete(@RequestBody Paquete paquete, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		String retornoRespuesta = paqueteBusiness.registrarPaquete(paquete, usuario);

		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/paquete/{paqueteId}")
	public ResponseEntity<Paquete> consultarPaquetePorHotel(@PathVariable String paqueteId, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		Paquete	paquete = paqueteBusiness.consultarPaquetePorHotel(usuario, paqueteId);
			
		if(paquete == null) {
			return new ResponseEntity<Paquete>(paquete, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Paquete>(paquete, HttpStatus.OK);
		}
	}
	
	@GetMapping("/paquete")
	public ResponseEntity<List<Paquete>> consultarPaquetesPorHotel(@RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		List<Paquete> paquetes = paqueteBusiness.consultarPaquetesPorHotel(usuario);
			
		if(paquetes == null) {
			return new ResponseEntity<List<Paquete>>(paquetes, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Paquete>>(paquetes, HttpStatus.OK);
		}
	}


}
