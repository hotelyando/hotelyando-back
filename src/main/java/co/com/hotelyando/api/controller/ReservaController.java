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

import co.com.hotelyando.core.business.ReservaBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Reserva;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservaController {
	
	private final ReservaBusiness reservaBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public ReservaController(ReservaBusiness reservaBusiness) {
		this.reservaBusiness = reservaBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/reserva")
	public ResponseEntity<String> registrarReserva(@RequestBody Reserva reserva, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		String retornoRespuesta = reservaBusiness.registrarReserva(reserva, usuario);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/reserva/{reservaId}")
	public ResponseEntity<Reserva> consultarReservaPorHotel(@PathVariable Integer reservaId, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		Reserva	reserva = reservaBusiness.consultarReservaPorHotel(usuario, reservaId);
			
		if(StringUtils.isEmpty(usuario)){
			return new ResponseEntity<Reserva>(reserva, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
		}	
	}
	
	@GetMapping("/reserva")
	public ResponseEntity<List<Reserva>> consultarReservasPorHotel(@RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		List<Reserva> reservas = reservaBusiness.consultarReservasPorHotel(usuario);
		
		if(reservas == null){
			return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
		}
	}

}
