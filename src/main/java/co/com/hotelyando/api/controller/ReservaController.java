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

import co.com.hotelyando.core.business.ReservaBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Reserva;

@RestController
public class ReservaController {
	
private ReservaBusiness reservaBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public ReservaController(ReservaBusiness reservaBusiness) {
		this.reservaBusiness = reservaBusiness;
	}
	
	public ReservaController() {
		utilidades = new Utilidades();
	}
	
	@PostMapping("/reserva")
	public ResponseEntity<String> registrarReserva(@RequestBody Reserva reserva, @RequestHeader Map<String, String> headers){
		
		String retornoRespuesta = "";
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			retornoRespuesta = reservaBusiness.registrarReserva(reserva, usuario);
		}catch (Exception e) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/reserva/{reservaId}")
	public ResponseEntity<Reserva> consultarReservaPorHotel(@PathVariable Integer reservaId, @RequestHeader Map<String, String> headers){
		
		Reserva reserva = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			reserva = reservaBusiness.consultarReservaPorHotel(usuario, reservaId);
			
		}catch (Exception e) {
			return new ResponseEntity<Reserva>(reserva, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
		
	}
	
	@GetMapping("/reserva")
	public ResponseEntity<List<Reserva>> consultarReservasPorHotel(@RequestHeader Map<String, String> headers){
		
		List<Reserva> reservas = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			reservas = reservaBusiness.consultarReservasPorHotel(usuario);
			
		}catch (Exception e) {
			return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
		
		
	}

}
