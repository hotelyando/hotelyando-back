package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Habitacion;

public interface IHabitacionRepository extends MongoRepository<Habitacion, Integer> {
	
	List<Habitacion> findByHotelId(String hotelId);
	Habitacion findByHotelIdAndHabitacionId(String hotelId, String habitacionId);
	

}
