package co.com.hotelyando.database.dao;

import java.util.List;

import co.com.hotelyando.database.model.Reserva;

public interface IReservaDao {
	
	String registrarReserva(Reserva reserva) throws Exception;
	List<Reserva> consultarReservasPorHotel(Integer hotelId) throws Exception;
	Reserva consultarReservaPorHotel(Integer hotelId, Integer reservaId) throws Exception;

}
