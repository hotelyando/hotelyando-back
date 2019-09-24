package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.HabitacionService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.database.model.Habitacion;
import co.com.hotelyando.database.model.Usuario;

@Service
public class HabitacionBusiness {

	private HabitacionService habitacionService;
	
	private Genericos<Usuario> genericos;
	private Usuario objetoUsuario;
	
	/*
	 * 
	 */
	public HabitacionBusiness(HabitacionService habitacionService) {
		this.habitacionService = habitacionService;
		
		genericos = new Genericos<>();
	}
	
	/*
	 * 
	 */
	public String registrarHabitacion(Habitacion habitacion, String usuario) {
		
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
	public List<Habitacion> consultarHabitacionesPorHotel(String usuario) {
		
		List<Habitacion> habitacions = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			habitacions = habitacionService.consultarHabitacionesPorHotel(objetoUsuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return habitacions;
	}

	/*
	 * 
	 */
	public Habitacion consultarHabitacionPorHotel(String usuario, Integer habitacionId) {
		
		Habitacion habitacion = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			habitacion = habitacionService.consultarHabitacionPorHotel(objetoUsuario.getHotelId(), habitacionId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return habitacion;
	}

}
