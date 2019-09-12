package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Habitacion;
import co.com.hotelyando.database.repository.IHabitacionRepository;

@Repository
public class HabitacionDao implements IHabitacionDao{
	
	@Autowired
	private IHabitacionRepository iHabitacionRepository;
	
	@Override
	public String registrarHabitacion(Habitacion habitacion) throws Exception {
		
		iHabitacionRepository.save(habitacion);
		
		return null;
	}

	@Override
	public List<Habitacion> consultarHabitacionesPorHotel(Integer hotelId) throws Exception {
		
		List<Habitacion> habitacions = null;
		
		habitacions = iHabitacionRepository.findByHotelId(hotelId);
		
		return habitacions;
	}

	@Override
	public Habitacion consultarHabitacionPorHotel(Integer hotelId, Integer habitacionId) throws Exception {
		
		Habitacion habitacion = null;
		
		habitacion = iHabitacionRepository.findByHotelIdAndHabitacionId(hotelId, habitacionId);
		
		return habitacion;
	}

}
