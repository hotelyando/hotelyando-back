package co.com.hotelyando.core.services;

import java.util.List;

import co.com.hotelyando.database.model.Habitacion;

public interface IHabitacionService {
	
	String registrarHabitacion(Habitacion habitacion);
	List<Habitacion> consultarHabitacionesPorHotel(Integer hotelId);
	Habitacion consultarHabitacionPorHotel(Integer hotelId, Integer habitacionId);

}
