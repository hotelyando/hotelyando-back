package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.IHabitacionDao;
import co.com.hotelyando.database.model.Habitacion;

@Service
public class HabitacionService implements IHabitacionService {

	@Autowired
	private IHabitacionDao iHabitacionDao;
	
	@Override
	public String registrarHabitacion(Habitacion habitacion) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = iHabitacionDao.registrarHabitacion(habitacion);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
	}

	@Override
	public List<Habitacion> consultarHabitacionesPorHotel(Integer hotelId) {
		
		List<Habitacion> habitacions = null;
		
		try {
			
			habitacions = iHabitacionDao.consultarHabitacionesPorHotel(hotelId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return habitacions;
	}

	@Override
	public Habitacion consultarHabitacionPorHotel(Integer hotelId, Integer habitacionId) {
		
		Habitacion habitacion = null;
		
		try {
			
			habitacion = iHabitacionDao.consultarHabitacionPorHotel(hotelId, habitacionId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return habitacion;
	}

}
