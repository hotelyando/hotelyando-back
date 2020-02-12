package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.model.RespuestasServicio;
import co.com.hotelyando.core.services.HabitacionService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.core.utilities.ImpresionEntidades;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Habitacion;
import co.com.hotelyando.database.model.Usuario;

@Service
public class HabitacionBusiness {

	private HabitacionService habitacionService;
	private RespuestaServicio<Habitacion> respuestaServicio;
	private RespuestasServicio<Habitacion> respuestasServicio;
	private Utilidades utilidades = null;
	private Genericos<Habitacion> genericos = null;
	
	public HabitacionBusiness(HabitacionService habitacionService) {
		this.habitacionService = habitacionService;
		
		respuestaServicio = new RespuestaServicio<Habitacion>();
		respuestasServicio = new RespuestasServicio<Habitacion>();
		utilidades = new Utilidades();
		genericos = new Genericos<Habitacion>();
	}
	
	public RespuestaServicio<Habitacion> registrarHabitacion(Habitacion habitacion, Usuario usuario) {
		
		String retornoMensaje = "";
		
		try {
			
			habitacion.setHabitacionId(utilidades.generadorId());
			habitacion.setHotelId(usuario.getHotelId());
			
			retornoMensaje = habitacionService.registrarHabitacion(habitacion);
			
			if(retornoMensaje.equals("")) {
				respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.NEGOCIO, ImpresionEntidades.HABITACION_REGISTRADA);
			}else {
				respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.ADVERTENCIA, retornoMensaje);
			}
			
		}catch (Exception e) {
			respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return respuestaServicio;
	}

	/*
	 * 
	 */
	public RespuestasServicio<Habitacion> consultarHabitacionesPorHotel(Usuario usuario) {
		
		List<Habitacion> habitacions = null;
		
		try {
			
			habitacions = habitacionService.consultarHabitacionesPorHotel(usuario.getHotelId());
			
			respuestasServicio = genericos.retornoMensajes(habitacions, ImpresionVariables.NEGOCIO, ImpresionEntidades.DATOS_RETORNADOS);
			
		}catch (Exception e) {
			respuestasServicio = genericos.retornoMensajes(habitacions, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return respuestasServicio;
	}

	/*
	 * 
	 */
	public RespuestaServicio<Habitacion> consultarHabitacionPorHotel(Usuario usuario, String habitacionId) {
		
		Habitacion habitacion = null;
		
		try {
			
			habitacion = habitacionService.consultarHabitacionPorHotel(usuario.getHotelId(), habitacionId);
			
			respuestaServicio = genericos.retornoMensaje(habitacion, ImpresionVariables.NEGOCIO, ImpresionEntidades.DATOS_RETORNADOS);
			
		}catch (Exception e) {
			respuestaServicio = genericos.retornoMensaje(habitacion, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return respuestaServicio;
	}

}
