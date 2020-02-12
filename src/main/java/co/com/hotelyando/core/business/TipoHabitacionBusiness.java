package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.model.RespuestasServicio;
import co.com.hotelyando.core.services.TipoHabitacionService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.core.utilities.ImpresionEntidades;
import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.TipoHabitacion;
import co.com.hotelyando.database.model.Usuario;

@Service
public class TipoHabitacionBusiness {

	private TipoHabitacionService tipoHabitacionService;
	private RespuestaServicio<TipoHabitacion> respuestaServicio;
	private RespuestasServicio<TipoHabitacion> respuestasServicio;
	private Utilidades utilidades = null;
	private Genericos<TipoHabitacion> genericos = null;
	
	
	public TipoHabitacionBusiness(TipoHabitacionService tipoHabitacionService) {
		this.tipoHabitacionService = tipoHabitacionService;
		
		respuestaServicio = new RespuestaServicio<TipoHabitacion>();
		respuestasServicio = new RespuestasServicio<TipoHabitacion>();
		utilidades = new Utilidades();
		genericos = new Genericos<TipoHabitacion>();
	}
	
	public RespuestaServicio<TipoHabitacion> registrarTipoHabitacion(TipoHabitacion tipoHabitacion, Usuario usuario) {
		
		String retornoMensaje = "";
		
		try {
			
			tipoHabitacion.setTipoId(utilidades.generadorId());
			tipoHabitacion.setHotelId(usuario.getHotelId());
			
			retornoMensaje = tipoHabitacionService.registrarTipoHabitacion(tipoHabitacion);
			
			if(retornoMensaje.equals("")) {
				respuestaServicio = genericos.retornoMensaje(null, ImpresionVariables.NEGOCIO, ImpresionEntidades.TIPO_HABITACION_REGISTRADA);
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
	public RespuestasServicio<TipoHabitacion> consultarTipoHabitacionesPorHotel(Usuario usuario) {
		
		List<TipoHabitacion> tipoHabitacions = null;
		
		try {
			
			tipoHabitacions = tipoHabitacionService.consultarTipoHabitacionesPorHotel(usuario.getHotelId());
			
			respuestasServicio = genericos.retornoMensajes(tipoHabitacions, ImpresionVariables.NEGOCIO, ImpresionEntidades.DATOS_RETORNADOS);
			
		}catch (Exception e) {
			respuestasServicio = genericos.retornoMensajes(tipoHabitacions, ImpresionVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return respuestasServicio;
	}
}
