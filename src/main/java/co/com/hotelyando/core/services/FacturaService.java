package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.FacturaDao;
import co.com.hotelyando.database.model.Factura;

@Service
public class FacturaService {

	private final FacturaDao facturaDao;
	
	public FacturaService(FacturaDao facturaDao) {
		this.facturaDao = facturaDao;
	}
	
	public String registrarFactura(Factura factura) throws Exception {
		
		String retornoMensaje = "";
			
		retornoMensaje = facturaDao.registrarFactura(factura);
			
		return retornoMensaje;
	}

	public List<Factura> consultarFacturasPorHotel(Integer hotelId) throws Exception {
		
		List<Factura> facturas = null;
		facturas = facturaDao.consultarFacturasPorHotel(hotelId);
			
		return facturas;
	}

	public Factura consultarFacturaPorHotel(Integer hotelId, Integer facturaId) throws Exception {
		
		Factura factura = null;
		factura = facturaDao.consultarFacturaPorHotel(hotelId, facturaId);
		
		return factura;
	}

}
