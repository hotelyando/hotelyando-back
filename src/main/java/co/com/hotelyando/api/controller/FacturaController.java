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

import co.com.hotelyando.core.business.FacturaBusiness;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Factura;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class FacturaController {
	
	private final FacturaBusiness facturaBusiness;
	
	private Utilidades utilidades;
	private String usuario;
	
	public FacturaController(FacturaBusiness facturaBusiness) {
		this.facturaBusiness = facturaBusiness;
		
		utilidades = new Utilidades();
	}
	
	@PostMapping("/factura")
	public ResponseEntity<String> registrarFactura(@RequestBody Factura factura, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		String retornoRespuesta = facturaBusiness.registrarFactura(factura, usuario);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/factura/{facturaId}")
	public ResponseEntity<Factura> consultarFacturaPorHotel(@PathVariable Integer facturaId, @RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		Factura	factura = facturaBusiness.consultarFacturaPorHotel(usuario, facturaId);
			
		if(factura == null) {
			return new ResponseEntity<Factura>(factura, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Factura>(factura, HttpStatus.OK);
		}
	}
	
	@GetMapping("/factura")
	public ResponseEntity<List<Factura>> consultarFacturasPorHotel(@RequestHeader Map<String, String> headers){
		
		usuario = utilidades.retornoTenant(headers, ImpresionVariables.TOKEN_HEADER);
			
		List<Factura> facturas = facturaBusiness.consultarFacturasPorHotel(usuario);
			
		if(facturas == null) {
			return new ResponseEntity<List<Factura>>(facturas, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Factura>>(facturas, HttpStatus.OK);
		}
	}


}
