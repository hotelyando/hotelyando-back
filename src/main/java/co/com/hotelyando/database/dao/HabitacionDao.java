package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Habitacion;
import co.com.hotelyando.database.repository.IHabitacionRepository;

@Repository
public class HabitacionDao{
	
	private final IHabitacionRepository iHabitacionRepository;
	
	public HabitacionDao(IHabitacionRepository iHabitacionRepository) {
		this.iHabitacionRepository = iHabitacionRepository;
	}
	
	
	public String registrarHabitacion(Habitacion habitacion) throws Exception {
		
		iHabitacionRepository.save(habitacion);
		
		return "Ok";
	}

	public List<Habitacion> consultarHabitacionesPorHotel(String hotelId) throws Exception {
		
		List<Habitacion> habitacions = null;
		habitacions = iHabitacionRepository.findByHotelId(hotelId);
		
		return habitacions;
	}

	public Habitacion consultarHabitacionPorHotel(String hotelId, String habitacionId) throws Exception {
		
		Habitacion habitacion = null;
		habitacion = iHabitacionRepository.findByHotelIdAndHabitacionId(hotelId, habitacionId);
		
		return habitacion;
	}

}
