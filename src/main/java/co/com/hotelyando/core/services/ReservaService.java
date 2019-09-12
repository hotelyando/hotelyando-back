package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.IReservaDao;
import co.com.hotelyando.database.model.Reserva;

@Service
public class ReservaService implements IReservaService {

	@Autowired
	private IReservaDao iReservaDao;
	
	@Override
	public String registrarReserva(Reserva reserva) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = iReservaDao.registrarReserva(reserva);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
	}

	@Override
	public List<Reserva> consultarReservasPorHotel(Integer hotelId) {
		
		List<Reserva> reservas = null;
		
		try {
			
			reservas = iReservaDao.consultarReservasPorHotel(hotelId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return reservas;
	}

	@Override
	public Reserva consultarReservaPorHotel(Integer hotelId, Integer reservaId) {
		
		Reserva reserva = null;
		
		try {
			
			reserva = iReservaDao.consultarReservaPorHotel(hotelId, reservaId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return reserva;
	}

}
