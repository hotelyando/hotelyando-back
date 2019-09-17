package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Empleado;

public interface IEmpleadoRepository extends MongoRepository<Empleado, Integer> {
	
	List<Empleado> findByHotelId(Integer hotelId);
	Empleado findByHotelIdAndEmpleadoId(Integer hotelId, Integer empleadoId);

}
