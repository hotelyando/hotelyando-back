package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.FacturaService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.database.model.Factura;
import co.com.hotelyando.database.model.Usuario;

@Service
public class FacturaBusiness {

	private final FacturaService facturaService;
	
	private Genericos<Usuario> genericos;
	private Usuario objetoUsuario;
	
	public FacturaBusiness(FacturaService facturaService) {
		this.facturaService = facturaService;
		
		genericos = new Genericos<>();
	}
	
	public String registrarFactura(Factura factura, String usuario) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = facturaService.registrarFactura(factura);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Factura> consultarFacturasPorHotel(String usuario) {
		
		List<Factura> facturas = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			facturas = facturaService.consultarFacturasPorHotel(objetoUsuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return facturas;
	}

	public Factura consultarFacturaPorHotel(String usuario, Integer facturaId) {
		
		Factura factura = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			factura = facturaService.consultarFacturaPorHotel(objetoUsuario.getHotelId(), facturaId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return factura;
	}

}
