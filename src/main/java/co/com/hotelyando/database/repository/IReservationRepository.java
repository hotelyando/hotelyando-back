package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Reservation;

@JaversSpringDataAuditable
public interface IReservationRepository extends MongoRepository<Reservation, String> {
	
	List<Reservation> findByHotelId(String hotelId);
	Reservation findByHotelIdAndUuid(String hotelId, String uuid);
	
	

}
