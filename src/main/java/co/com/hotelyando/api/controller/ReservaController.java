package co.com.hotelyando.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.services.IReservaService;
import co.com.hotelyando.database.model.Reserva;

@RestController
public class ReservaController {
	
	@Autowired
	private IReservaService iReservaService;
	
	@PostMapping("/reserva")
	public ResponseEntity<String> registrarReserva(@RequestBody Reserva reserva){
		
		String retornoRespuesta = "";
		
		retornoRespuesta = iReservaService.registrarReserva(reserva); 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/reserva/{hotelId}/{reservaId}")
	public ResponseEntity<Reserva> consultarReservaPorHotel(@PathVariable Integer hotelId, Integer reservaId){
		
		Reserva reserva = null;
		
		reserva = iReservaService.consultarReservaPorHotel(hotelId, reservaId);
		
		return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
	}
	
	@GetMapping("/reserva/{hotelId}")
	public ResponseEntity<List<Reserva>> consultarReservasPorHotel(@PathVariable Integer hotelId){
		
		List<Reserva> reservas = null;
		
		reservas = iReservaService.consultarReservasPorHotel(hotelId);
		
		return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
	}


}
