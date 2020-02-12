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

import co.com.hotelyando.core.business.HabitacionBusiness;
import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.model.RespuestasServicio;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Habitacion;
import co.com.hotelyando.database.model.Usuario;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Habitacion")
public class HabitacionController {
	
	private final HabitacionBusiness habitacionBusiness;
	
	private Utilidades utilidades;
	private Usuario usuario;
	
	public HabitacionController(HabitacionBusiness habitacionBusiness) {
		this.habitacionBusiness = habitacionBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/habitacion")
	public ResponseEntity<RespuestaServicio<Habitacion>> registrarHabitacion(@RequestBody Habitacion habitacion, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		RespuestaServicio<Habitacion> retornoRespuesta = habitacionBusiness.registrarHabitacion(habitacion, usuario);
		
		return new ResponseEntity<RespuestaServicio<Habitacion>>(retornoRespuesta, HttpStatus.OK);
		
	}
	
	@GetMapping("/habitacion/{habitacionId}")
	public ResponseEntity<RespuestaServicio<Habitacion>> consultarHabitacionPorHotel(@PathVariable String habitacionId, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		RespuestaServicio<Habitacion> respuestaServicio = habitacionBusiness.consultarHabitacionPorHotel(usuario, habitacionId);
			
		return new ResponseEntity<RespuestaServicio<Habitacion>>(respuestaServicio, HttpStatus.OK);
		
	}
	
	@GetMapping("/habitacion")
	public ResponseEntity<RespuestasServicio<Habitacion>> consultarHabitacionesPorHotel(@RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		RespuestasServicio<Habitacion> respuestasServicio = habitacionBusiness.consultarHabitacionesPorHotel(usuario);
			
		return new ResponseEntity<RespuestasServicio<Habitacion>>(respuestasServicio, HttpStatus.OK);
		
	}


}
