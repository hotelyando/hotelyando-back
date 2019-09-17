package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Factura;

public interface IFacturaRepository extends MongoRepository<Factura, Integer> {
	
	List<Factura> findByHotelId(Integer hotelId);
	Factura findByHotelIdAndFacturaId(Integer hotelId, Integer facturaId);
	
}
