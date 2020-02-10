package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Factura;
import co.com.hotelyando.database.repository.IFacturaRepository;

@Repository
public class FacturaDao {

	private final IFacturaRepository iFacturaRepository;
	
	public FacturaDao(IFacturaRepository iFacturaRepository) {
		this.iFacturaRepository = iFacturaRepository;
	}
	
	public String registrarFactura(Factura factura) throws Exception {
		
		iFacturaRepository.save(factura);
		
		return "Ok";
	}

	
	public List<Factura> consultarFacturasPorHotel(String hotelId) throws Exception {
		
		List<Factura> facturas = null;
		
		facturas = iFacturaRepository.findByHotelId(hotelId);
		
		return facturas;
	}

	
	public Factura consultarFacturaPorHotel(String hotelId, String facturaId) throws Exception {
		
		Factura factura = null;
		
		factura = iFacturaRepository.findByHotelIdAndFacturaId(hotelId, facturaId);
		
		return factura;
	}

}
