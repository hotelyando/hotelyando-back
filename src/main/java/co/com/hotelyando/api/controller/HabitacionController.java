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

import co.com.hotelyando.core.services.IHabitacionService;
import co.com.hotelyando.database.model.Habitacion;

@RestController
public class HabitacionController {
	
	@Autowired
	private IHabitacionService iHabitacionService;
	
	@PostMapping("/habitacion")
	public ResponseEntity<String> registrarHabitacion(@RequestBody Habitacion habitacion){
		
		String retornoRespuesta = "";
		
		retornoRespuesta = iHabitacionService.registrarHabitacion(habitacion); 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/habitacion/{hotelId}/{habitacionId}")
	public ResponseEntity<Habitacion> consultarHabitacionPorHotel(@PathVariable Integer hotelId, Integer habitacionId){
		
		Habitacion habitacion = null;
		
		habitacion = iHabitacionService.consultarHabitacionPorHotel(hotelId, habitacionId);
		
		return new ResponseEntity<Habitacion>(habitacion, HttpStatus.OK);
	}
	
	@GetMapping("/habitacion/{hotelId}")
	public ResponseEntity<List<Habitacion>> consultarHabitacionesPorHotel(@PathVariable Integer hotelId){
		
		List<Habitacion> habitaciones = null;
		
		habitaciones = iHabitacionService.consultarHabitacionesPorHotel(hotelId);
		
		return new ResponseEntity<List<Habitacion>>(habitaciones, HttpStatus.OK);
	}


}
