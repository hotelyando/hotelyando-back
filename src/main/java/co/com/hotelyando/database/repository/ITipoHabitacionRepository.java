package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.TipoHabitacion;

public interface ITipoHabitacionRepository extends MongoRepository<TipoHabitacion, Integer> {
	
	List<TipoHabitacion> findByHotelId(String hotelId);

}
