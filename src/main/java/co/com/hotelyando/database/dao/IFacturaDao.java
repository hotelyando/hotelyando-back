package co.com.hotelyando.database.dao;

import java.util.List;

import co.com.hotelyando.database.model.Factura;

public interface IFacturaDao {

	String registrarFactura(Factura factura) throws Exception;
	List<Factura> consultarFacturasPorHotel(Integer hotelId) throws Exception;
	Factura consultarFacturaPorHotel(Integer hotelId, Integer facturaId) throws Exception;
}
