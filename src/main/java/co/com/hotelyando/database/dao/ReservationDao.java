package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Reservation;
import co.com.hotelyando.database.repository.IReservationRepository;

@Repository
public class ReservationDao {
	
	private final IReservationRepository iReservationRepository;
	
	public ReservationDao(IReservationRepository iReservationRepository) {
		this.iReservationRepository = iReservationRepository;
	}
	
	public void save(Reservation reservation) throws MongoException, Exception {
		iReservationRepository.save(reservation);
	}

	public void update(Reservation reservation) throws MongoException, Exception {
		iReservationRepository.save(reservation);
	}
	
	public List<Reservation> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Reservation> reservations = null;
		reservations = iReservationRepository.findByHotelId(hotelId);
		
		return reservations;
	}

	public Reservation findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Reservation reservation = null;
		reservation = iReservationRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return reservation;
	}

}
