package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.ImpresionEntidades;
import co.com.hotelyando.database.dao.HabitacionDao;
import co.com.hotelyando.database.model.Habitacion;
import co.com.hotelyando.database.model.Item;

@Service
public class HabitacionService {

	private HabitacionDao habitacionDao;
	
	public HabitacionService(HabitacionDao habitacionDao) {
		this.habitacionDao = habitacionDao;
	}
	
	
	public String registrarHabitacion(Habitacion habitacion) throws Exception {
		
		String retornoMensaje = "";
		
		if(StringUtils.isBlank(habitacion.getHabitacionId())) {
			retornoMensaje = ImpresionEntidades.HABITACION_VALIDACION_ID;
		}else if(StringUtils.isBlank(habitacion.getHotelId())) {
			retornoMensaje = ImpresionEntidades.HABITACION_HOTEL_ID;
		}else if(StringUtils.isBlank(habitacion.getCheckIn())) {
			retornoMensaje = ImpresionEntidades.HABITACION_CHECK_IN;
		}else if(StringUtils.isBlank(habitacion.getCheckOut())) {
			retornoMensaje = ImpresionEntidades.HABITACION_CHECK_OUT;
		}else if(StringUtils.isBlank(habitacion.getTipo())) {
			retornoMensaje = ImpresionEntidades.HABITACION_TIPO;
		}else if(StringUtils.isBlank(habitacion.getItem().getItemId())) {
			retornoMensaje = ImpresionEntidades.HABITACION_ITEM;
		}else {
			habitacionDao.registrarHabitacion(habitacion);
		}
		
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
