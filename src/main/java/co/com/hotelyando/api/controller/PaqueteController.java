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

import co.com.hotelyando.core.services.IPaqueteService;
import co.com.hotelyando.database.model.Paquete;

@RestController
public class PaqueteController {
	
	@Autowired
	private IPaqueteService iPaqueteService;
	
	@PostMapping("/paquete")
	public ResponseEntity<String> registrarPaquete(@RequestBody Paquete paquete){
		
		String retornoRespuesta = "";
		
		retornoRespuesta = iPaqueteService.registrarPaquete(paquete); 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/paquete/{hotelId}/{paqueteId}")
	public ResponseEntity<Paquete> consultarPaquetePorHotel(@PathVariable Integer hotelId, Integer paqueteId){
		
		Paquete paquete = null;
		
		paquete = iPaqueteService.consultarPaquetePorHotel(hotelId, paqueteId);
		
		return new ResponseEntity<Paquete>(paquete, HttpStatus.OK);
	}
	
	@GetMapping("/paquete/{hotelId}")
	public ResponseEntity<List<Paquete>> consultarPaquetesPorHotel(@PathVariable Integer hotelId){
		
		List<Paquete> paquetes = null;
		
		paquetes = iPaqueteService.consultarPaquetesPorHotel(hotelId);
		
		return new ResponseEntity<List<Paquete>>(paquetes, HttpStatus.OK);
	}


}
