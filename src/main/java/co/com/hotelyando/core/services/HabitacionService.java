package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.HabitacionDao;
import co.com.hotelyando.database.model.Habitacion;

@Service
public class HabitacionService {

	private HabitacionDao habitacionDao;
	
	public HabitacionService(HabitacionDao habitacionDao) {
		this.habitacionDao = habitacionDao;
	}
	
	
	public String registrarHabitacion(Habitacion habitacion) throws Exception {
		
		String retornoMensaje = "";
			
		retornoMensaje = habitacionDao.registrarHabitacion(habitacion);
		
		return retornoMensaje;
	}

	public List<Habitacion> consultarHabitacionesPorHotel(String hotelId) throws Exception {
		
		List<Habitacion> habitacions = null;
		habitacions = habitacionDao.consultarHabitacionesPorHotel(hotelId);
		
		return habitacions;
	}

	public Habitacion consultarHabitacionPorHotel(String hotelId, String habitacionId) throws Exception {
		
		Habitacion habitacion = null;
		habitacion = habitacionDao.consultarHabitacionPorHotel(hotelId, habitacionId);
		
		return habitacion;
	}

}
