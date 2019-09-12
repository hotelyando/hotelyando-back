package co.com.hotelyando.core.services;

import java.util.List;

import co.com.hotelyando.database.model.Factura;

public interface IFacturaService {

	String registrarFactura(Factura factura);
	List<Factura> consultarFacturasPorHotel(Integer hotelId);
	Factura consultarFacturaPorHotel(Integer hotelId, Integer facturaId);
}
