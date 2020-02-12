package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.ImpresionEntidades;
import co.com.hotelyando.database.dao.TipoHabitacionDao;
import co.com.hotelyando.database.model.TipoHabitacion;

@Service
public class TipoHabitacionService {

	private TipoHabitacionDao tipoTipoHabitacionDao;
	
	public TipoHabitacionService(TipoHabitacionDao tipoTipoHabitacionDao) {
		this.tipoTipoHabitacionDao = tipoTipoHabitacionDao;
	}
	
	
	public String registrarTipoHabitacion(TipoHabitacion tipoTipoHabitacion) throws Exception {
		
		String retornoMensaje = "";
		
		if(StringUtils.isBlank(tipoTipoHabitacion.getTipoId())) {
			retornoMensaje = ImpresionEntidades.TIPO_HABITACION_ID;
		}else if(StringUtils.isBlank(tipoTipoHabitacion.getHotelId())) {
			retornoMensaje = ImpresionEntidades.TIPO_HABITACION_HOTEL_ID;
		}else if(StringUtils.isBlank(tipoTipoHabitacion.getDescripcion())) {
			retornoMensaje = ImpresionEntidades.TIPO_HABITACION_DESCRIPCION;
		}else if(StringUtils.isBlank(tipoTipoHabitacion.getPrecioDia().toString())) {
			retornoMensaje = ImpresionEntidades.TIPO_HABITACION_PRECIO_DIA;
		}else if(StringUtils.isBlank(tipoTipoHabitacion.getPrecioHora().toString())) {
			retornoMensaje = ImpresionEntidades.TIPO_HABITACION_PRECIO_HORA;
		}else {
			tipoTipoHabitacionDao.registrarTipoHabitacion(tipoTipoHabitacion);
		}
		
		return retornoMensaje;
	}

	public List<TipoHabitacion> consultarTipoHabitacionesPorHotel(String hotelId) throws Exception {
		
		List<TipoHabitacion> tipoTipoHabitacions = null;
		tipoTipoHabitacions = tipoTipoHabitacionDao.consultarTipoHabitacionesPorHotel(hotelId);
		
		return tipoTipoHabitacions;
	}

}
