package co.com.hotelyando.api.controller;

import java.util.List;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.services.IEmpleadoService;
import co.com.hotelyando.database.model.Empleado;

@RestController
public class EmpleadoController {
	
	@Autowired
	private IEmpleadoService iEmpleadoService;
	
	@PostMapping("/empleado")
	public ResponseEntity<String> registrarEmpleado(@RequestBody Empleado empleado){
		
		String retornoRespuesta = "";
		
		retornoRespuesta = iEmpleadoService.registrarEmpleado(empleado); 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	
	@GetMapping("/empleado/{hotelId}/{empleadoId}")
	public ResponseEntity<Empleado> consultarEmpleadoPorHotel(@PathVariable Integer hotelId, Integer empleadoId){
		
		Empleado empleado = null;
		
		empleado = iEmpleadoService.consultarEmpleadoPorHotel(hotelId, empleadoId);
		
		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
	}
	
	@GetMapping("/empleado/{hotelId}")
	public ResponseEntity<List<Empleado>> consultarEmpleadosPorHotel(@PathVariable Integer hotelId){
		
		List<Empleado> empleados = null;
		
		empleados = iEmpleadoService.consultarEmpleadosPorHotel(hotelId);
		
		return new ResponseEntity<List<Empleado>>(empleados, HttpStatus.OK);
	}

}
