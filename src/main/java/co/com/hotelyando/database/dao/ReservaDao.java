package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Reserva;
import co.com.hotelyando.database.repository.IReservaRepository;

@Repository
public class ReservaDao implements IReservaDao{
	
	@Autowired
	private IReservaRepository iReservaRepository;
	
	@Override
	public String registrarReserva(Reserva reserva) throws Exception {
		
		iReservaRepository.save(reserva);
		
		return null;
	}

	@Override
	public List<Reserva> consultarReservasPorHotel(Integer hotelId) throws Exception {
		
		List<Reserva> reservas = null;
		
		reservas = iReservaRepository.findByHotelId(hotelId);
		
		return reservas;
	}

	@Override
	public Reserva consultarReservaPorHotel(Integer hotelId, Integer reservaId) throws Exception {
		
		Reserva reserva = null;
		
		reserva = iReservaRepository.findByHotelIdAndReservaId(hotelId, reservaId);
		
		return reserva;
	}

}
