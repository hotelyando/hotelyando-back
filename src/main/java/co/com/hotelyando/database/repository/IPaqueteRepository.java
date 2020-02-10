package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Paquete;

public interface IPaqueteRepository extends MongoRepository<Paquete, Integer> {
	
	List<Paquete> findByHotelId(String hotelId);
	Paquete findByHotelIdAndPaqueteId(String hotelId, String paqueteId);
	
}