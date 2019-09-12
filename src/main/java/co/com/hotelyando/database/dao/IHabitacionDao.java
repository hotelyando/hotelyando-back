package co.com.hotelyando.database.dao;

import java.util.List;

import co.com.hotelyando.database.model.Habitacion;

public interface IHabitacionDao {
	
	String registrarHabitacion(Habitacion habitacion) throws Exception;
	List<Habitacion> consultarHabitacionesPorHotel(Integer hotelId) throws Exception;
	Habitacion consultarHabitacionPorHotel(Integer hotelId, Integer habitacionId) throws Exception;

}
