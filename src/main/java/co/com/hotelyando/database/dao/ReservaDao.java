package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Reserva;
import co.com.hotelyando.database.repository.IReservaRepository;

@Repository
public class ReservaDao {
	
	private final IReservaRepository iReservaRepository;
	
	public ReservaDao(IReservaRepository iReservaRepository) {
		this.iReservaRepository = iReservaRepository;
	}
	
	public String registrarReserva(Reserva reserva) throws Exception {
		
		iReservaRepository.save(reserva);
		
		return "Ok";
	}

	public List<Reserva> consultarReservasPorHotel(String hotelId) throws Exception {
		
		List<Reserva> reservas = null;
		reservas = iReservaRepository.findByHotelId(hotelId);
		
		return reservas;
	}

	public Reserva consultarReservaPorHotel(String hotelId, String reservaId) throws Exception {
		
		Reserva reserva = null;
		reserva = iReservaRepository.findByHotelIdAndReservaId(hotelId, reservaId);
		
		return reserva;
	}

}
