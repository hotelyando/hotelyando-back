package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.ReservationDao;
import co.com.hotelyando.database.model.Reservation;

@Service
public class ReservationService {

	private final ReservationDao reservationDao;
	
	public ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	
	public String save(Reservation reservation) throws Exception {
		
		String messageReturn = "";
			
		messageReturn = reservationDao.save(reservation);
		
		return messageReturn;
	}

	
	public List<Reservation> findByHotelId(String hotelId) throws Exception {
		
		List<Reservation> reservations = null;
		reservations = reservationDao.findByHotelId(hotelId);
		
		return reservations;
	}

	public Reservation findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Reservation reservation = null;
		reservation = reservationDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return reservation;
	}

}
