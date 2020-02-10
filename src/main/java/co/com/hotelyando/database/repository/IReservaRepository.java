package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Reserva;

public interface IReservaRepository extends MongoRepository<Reserva, Integer> {
	
	List<Reserva> findByHotelId(String hotelId);
	Reserva findByHotelIdAndReservaId(String hotelId, String reservaId);
	
	

}
