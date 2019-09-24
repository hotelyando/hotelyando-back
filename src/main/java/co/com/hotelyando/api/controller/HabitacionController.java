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

import co.com.hotelyando.core.business.HabitacionBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Habitacion;

@RestController
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
		
		String retornoRespuesta = "";
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			retornoRespuesta = habitacionBusiness.registrarHabitacion(habitacion, usuario);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NOT_IMPLEMENTED);
		} 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/habitacion/{habitacionId}")
	public ResponseEntity<Habitacion> consultarHabitacionPorHotel(@PathVariable Integer habitacionId, @RequestHeader Map<String, String> headers){
		
		Habitacion habitacion = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			habitacion = habitacionBusiness.consultarHabitacionPorHotel(usuario, habitacionId);
			
		}catch (Exception e) {
			return new ResponseEntity<Habitacion>(habitacion, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<Habitacion>(habitacion, HttpStatus.OK);
	}
	
	@GetMapping("/habitacion")
	public ResponseEntity<List<Habitacion>> consultarHabitacionesPorHotel(@RequestHeader Map<String, String> headers){
		
		List<Habitacion> habitaciones = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			habitaciones = habitacionBusiness.consultarHabitacionesPorHotel(usuario);
			
		}catch (Exception e) {
			return new ResponseEntity<List<Habitacion>>(habitaciones, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<List<Habitacion>>(habitaciones, HttpStatus.OK);
	}


}
