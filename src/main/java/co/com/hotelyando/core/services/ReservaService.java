package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.ReservaDao;
import co.com.hotelyando.database.model.Reserva;

@Service
public class ReservaService {

	private final ReservaDao reservaDao;
	
	public ReservaService(ReservaDao reservaDao) {
		this.reservaDao = reservaDao;
	}
	
	
	public String registrarReserva(Reserva reserva) throws Exception {
		
		String retornoMensaje = "";
			
		retornoMensaje = reservaDao.registrarReserva(reserva);
		
		return retornoMensaje;
	}

	
	public List<Reserva> consultarReservasPorHotel(Integer hotelId) throws Exception {
		
		List<Reserva> reservas = null;
		reservas = reservaDao.consultarReservasPorHotel(hotelId);
		
		return reservas;
	}

	public Reserva consultarReservaPorHotel(Integer hotelId, Integer reservaId) throws Exception {
		
		Reserva reserva = null;
		reserva = reservaDao.consultarReservaPorHotel(hotelId, reservaId);
		
		return reserva;
	}

}
