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

import co.com.hotelyando.core.business.FacturaBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Factura;

@RestController
public class FacturaController {
	
	private FacturaBusiness facturaBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public FacturaController(FacturaBusiness facturaBusiness) {
		this.facturaBusiness = facturaBusiness;
	}
	
	public FacturaController() {
		utilidades = new Utilidades();
	}
	
	@PostMapping("/factura")
	public ResponseEntity<String> registrarFactura(@RequestBody Factura factura, @RequestHeader Map<String, String> headers){
		
		String retornoRespuesta = "";
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			retornoRespuesta = facturaBusiness.registrarFactura(factura, usuario);
		}catch (Exception e) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
	}
	
	@GetMapping("/factura/{facturaId}")
	public ResponseEntity<Factura> consultarFacturaPorHotel(@PathVariable Integer facturaId, @RequestHeader Map<String, String> headers){
		
		Factura factura = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			factura = facturaBusiness.consultarFacturaPorHotel(usuario, facturaId);
			
		}catch (Exception e) {
			return new ResponseEntity<Factura>(factura, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<Factura>(factura, HttpStatus.OK);
		
	}
	
	@GetMapping("/factura")
	public ResponseEntity<List<Factura>> consultarFacturasPorHotel(@RequestHeader Map<String, String> headers){
		
		List<Factura> facturas = null;
		
		try {
			
			usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
			facturas = facturaBusiness.consultarFacturasPorHotel(usuario);
			
		}catch (Exception e) {
			return new ResponseEntity<List<Factura>>(facturas, HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<List<Factura>>(facturas, HttpStatus.OK);
		
		
	}


}
