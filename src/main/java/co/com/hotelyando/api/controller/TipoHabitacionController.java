package co.com.hotelyando.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.TipoHabitacionBusiness;
import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.model.RespuestasServicio;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.TipoHabitacion;
import co.com.hotelyando.database.model.Usuario;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "TipoHabitacion")
public class TipoHabitacionController {
	
	private final TipoHabitacionBusiness tipoHabitacionBusiness;
	
	private Utilidades utilidades;
	private Usuario usuario;
	
	public TipoHabitacionController(TipoHabitacionBusiness tipoHabitacionBusiness) {
		this.tipoHabitacionBusiness = tipoHabitacionBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/tipoTipoHabitacion")
	public ResponseEntity<RespuestaServicio<TipoHabitacion>> registrarTipoHabitacion(@RequestBody TipoHabitacion tipoTipoHabitacion, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		RespuestaServicio<TipoHabitacion> retornoRespuesta = tipoHabitacionBusiness.registrarTipoHabitacion(tipoTipoHabitacion, usuario);
		
		return new ResponseEntity<RespuestaServicio<TipoHabitacion>>(retornoRespuesta, HttpStatus.OK);
		
	}
	
	@GetMapping("/tipoTipoHabitacion")
	public ResponseEntity<RespuestasServicio<TipoHabitacion>> consultarTipoHabitacionesPorHotel(@RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
		RespuestasServicio<TipoHabitacion> respuestasServicio = tipoHabitacionBusiness.consultarTipoHabitacionesPorHotel(usuario);
			
		return new ResponseEntity<RespuestasServicio<TipoHabitacion>>(respuestasServicio, HttpStatus.OK);
		
	}


}
