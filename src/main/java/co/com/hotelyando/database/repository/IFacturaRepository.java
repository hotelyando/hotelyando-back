package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Factura;

public interface IFacturaRepository extends MongoRepository<Factura, Integer> {
	
	List<Factura> findByHotelId(String hotelId);
	Factura findByHotelIdAndFacturaId(String hotelId, String facturaId);
	
}
