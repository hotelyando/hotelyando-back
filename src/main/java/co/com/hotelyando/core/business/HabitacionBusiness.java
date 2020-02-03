package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.HabitacionService;
import co.com.hotelyando.database.model.Habitacion;
import co.com.hotelyando.database.model.Usuario;

@Service
public class HabitacionBusiness {

	private HabitacionService habitacionService;
	
	public HabitacionBusiness(HabitacionService habitacionService) {
		this.habitacionService = habitacionService;
	}
	
	public String registrarHabitacion(Habitacion habitacion, Usuario usuario) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = habitacionService.registrarHabitacion(habitacion);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
	}

	/*
	 * 
	 */
	public List<Habitacion> consultarHabitacionesPorHotel(Usuario usuario) {
		
		List<Habitacion> habitacions = null;
		
		try {
			
			habitacions = habitacionService.consultarHabitacionesPorHotel(usuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return habitacions;
	}

	/*
	 * 
	 */
	public Habitacion consultarHabitacionPorHotel(Usuario usuario, Integer habitacionId) {
		
		Habitacion habitacion = null;
		
		try {
			
			habitacion = habitacionService.consultarHabitacionPorHotel(usuario.getHotelId(), habitacionId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return habitacion;
	}

}
