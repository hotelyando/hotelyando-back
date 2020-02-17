package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.ReservaService;
import co.com.hotelyando.database.model.Reserva;
import co.com.hotelyando.database.model.User;

@Service
public class ReservaBusiness {

private final ReservaService reservaService;
	
	public ReservaBusiness(ReservaService reservaService) {
		this.reservaService = reservaService;
	}
	
	public String registrarReserva(Reserva reserva, User user) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = reservaService.registrarReserva(reserva);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Reserva> consultarReservasPorHotel(User user) {
		
		List<Reserva> reservas = null;
		
		try {
			
			reservas = reservaService.consultarReservasPorHotel(user.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return reservas;
	}

	public Reserva consultarReservaPorHotel(User user, String reservaId) {
		
		Reserva reserva = null;
		
		try {
			
			reserva = reservaService.consultarReservaPorHotel(user.getHotelId(), reservaId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return reserva;
	}

}
