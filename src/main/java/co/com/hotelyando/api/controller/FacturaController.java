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

import co.com.hotelyando.core.services.IFacturaService;
import co.com.hotelyando.database.model.Factura;

@RestController
public class FacturaController {
	
	@Autowired
	private IFacturaService iFacturaService;
	
	@PostMapping("/factura")
	public ResponseEntity<String> registrarFactura(@RequestBody Factura factura){
		
		String retornoRespuesta = "";
		
		retornoRespuesta = iFacturaService.registrarFactura(factura); 
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/factura/{hotelId}/{facturaId}")
	public ResponseEntity<Factura> consultarFacturaPorHotel(@PathVariable Integer hotelId, Integer facturaId){
		
		Factura factura = null;
		
		factura = iFacturaService.consultarFacturaPorHotel(hotelId, facturaId);
		
		return new ResponseEntity<Factura>(factura, HttpStatus.OK);
	}
	
	@GetMapping("/factura/{hotelId}")
	public ResponseEntity<List<Factura>> consultarFacturasPorHotel(@PathVariable Integer hotelId){
		
		List<Factura> facturas = null;
		
		facturas = iFacturaService.consultarFacturasPorHotel(hotelId);
		
		return new ResponseEntity<List<Factura>>(facturas, HttpStatus.OK);
	}


}
