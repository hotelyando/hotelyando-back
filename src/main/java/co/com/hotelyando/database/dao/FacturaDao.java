package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Factura;
import co.com.hotelyando.database.repository.IFacturaRepository;

@Repository
public class FacturaDao implements IFacturaDao {

	@Autowired
	private IFacturaRepository iFacturaRepository;
	
	@Override
	public String registrarFactura(Factura factura) throws Exception {
		
		iFacturaRepository.save(factura);
		
		return null;
	}

	@Override
	public List<Factura> consultarFacturasPorHotel(Integer hotelId) throws Exception {
		
		List<Factura> facturas = null;
		
		facturas = iFacturaRepository.findByHotelId(hotelId);
		
		return facturas;
	}

	@Override
	public Factura consultarFacturaPorHotel(Integer hotelId, Integer facturaId) throws Exception {
		
		Factura factura = null;
		
		factura = iFacturaRepository.findByHotelIdAndFacturaId(hotelId, facturaId);
		
		return factura;
	}

}
