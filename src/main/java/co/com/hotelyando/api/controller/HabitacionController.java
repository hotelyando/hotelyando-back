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

import co.com.hotelyando.core.business.HabitacionBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Habitacion;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class HabitacionController {
	
	private HabitacionBusiness habitacionBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public HabitacionController(HabitacionBusiness habitacionBusiness) {
		this.habitacionBusiness = habitacionBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/habitacion")
	public ResponseEntity<String> registrarHabitacion(@RequestBody Habitacion habitacion, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		String retornoRespuesta = habitacionBusiness.registrarHabitacion(habitacion, usuario);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/habitacion/{habitacionId}")
	public ResponseEntity<Habitacion> consultarHabitacionPorHotel(@PathVariable Integer habitacionId, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		Habitacion habitacion = habitacionBusiness.consultarHabitacionPorHotel(usuario, habitacionId);
			
		if(habitacion == null) {
			return new ResponseEntity<Habitacion>(habitacion, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Habitacion>(habitacion, HttpStatus.OK);
		}
	}
	
	@GetMapping("/habitacion")
	public ResponseEntity<List<Habitacion>> consultarHabitacionesPorHotel(@RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		List<Habitacion> habitaciones = habitacionBusiness.consultarHabitacionesPorHotel(usuario);
			
		if(habitaciones == null) {
			return new ResponseEntity<List<Habitacion>>(habitaciones, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Habitacion>>(habitaciones, HttpStatus.OK);
		}
	}


}
