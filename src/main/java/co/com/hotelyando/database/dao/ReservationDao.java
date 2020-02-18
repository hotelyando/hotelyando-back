package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Reservation;
import co.com.hotelyando.database.repository.IReservationRepository;

@Repository
public class ReservationDao {
	
	private final IReservationRepository iReservationRepository;
	
	public ReservationDao(IReservationRepository iReservationRepository) {
		this.iReservationRepository = iReservationRepository;
	}
	
	public String save(Reservation reservation) throws Exception {
		
		iReservationRepository.save(reservation);
		
		return "Ok";
	}

	public List<Reservation> findByHotelId(String hotelId) throws Exception {
		
		List<Reservation> reservations = null;
		reservations = iReservationRepository.findByHotelId(hotelId);
		
		return reservations;
	}

	public Reservation findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Reservation reservation = null;
		reservation = iReservationRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return reservation;
	}

}
