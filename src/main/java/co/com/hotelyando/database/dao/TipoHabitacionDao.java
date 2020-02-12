package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.TipoHabitacion;
import co.com.hotelyando.database.repository.ITipoHabitacionRepository;

@Repository
public class TipoHabitacionDao{
	
	private final ITipoHabitacionRepository iTipoHabitacionRepository;
	
	public TipoHabitacionDao(ITipoHabitacionRepository iTipoHabitacionRepository) {
		this.iTipoHabitacionRepository = iTipoHabitacionRepository;
	}
	
	
	public String registrarTipoHabitacion(TipoHabitacion tipoHabitacion) throws Exception {
		
		iTipoHabitacionRepository.save(tipoHabitacion);
		
		return "Ok";
	}

	public List<TipoHabitacion> consultarTipoHabitacionesPorHotel(String hotelId) throws Exception {
		
		List<TipoHabitacion> tipoHabitacions = null;
		tipoHabitacions = iTipoHabitacionRepository.findByHotelId(hotelId);
		
		return tipoHabitacions;
	}

}
