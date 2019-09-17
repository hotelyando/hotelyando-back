package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.IFacturaDao;
import co.com.hotelyando.database.model.Factura;

@Service
public class FacturaService implements IFacturaService {

	@Autowired
	private IFacturaDao iFacturaDao;
	
	@Override
	public String registrarFactura(Factura factura) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = iFacturaDao.registrarFactura(factura);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
	}

	@Override
	public List<Factura> consultarFacturasPorHotel(Integer hotelId) {
		
		List<Factura> facturas = null;
		
		try {
			
			facturas = iFacturaDao.consultarFacturasPorHotel(hotelId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return facturas;
	}

	@Override
	public Factura consultarFacturaPorHotel(Integer hotelId, Integer facturaId) {
		
		Factura factura = null;
		
		try {
			
			factura = iFacturaDao.consultarFacturaPorHotel(hotelId, facturaId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return factura;
	}

}
