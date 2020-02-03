package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.FacturaService;
import co.com.hotelyando.database.model.Factura;
import co.com.hotelyando.database.model.Usuario;

@Service
public class FacturaBusiness {

	private final FacturaService facturaService;
	private Usuario objetoUsuario;
	
	public FacturaBusiness(FacturaService facturaService) {
		this.facturaService = facturaService;
	}
	
	public String registrarFactura(Factura factura, Usuario usuario) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = facturaService.registrarFactura(factura);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Factura> consultarFacturasPorHotel(Usuario usuario) {
		
		List<Factura> facturas = null;
		
		try {
			
			facturas = facturaService.consultarFacturasPorHotel(objetoUsuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return facturas;
	}

	public Factura consultarFacturaPorHotel(Usuario usuario, Integer facturaId) {
		
		Factura factura = null;
		
		try {
			
			factura = facturaService.consultarFacturaPorHotel(objetoUsuario.getHotelId(), facturaId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return factura;
	}

}
