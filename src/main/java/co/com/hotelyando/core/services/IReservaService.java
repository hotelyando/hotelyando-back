package co.com.hotelyando.core.services;

import java.util.List;

import co.com.hotelyando.database.model.Reserva;

public interface IReservaService {
	
	String registrarReserva(Reserva reserva);
	List<Reserva> consultarReservasPorHotel(Integer hotelId);
	Reserva consultarReservaPorHotel(Integer hotelId, Integer reservaId);

}
