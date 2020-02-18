package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.ReservationService;
import co.com.hotelyando.database.model.Reservation;
import co.com.hotelyando.database.model.User;

@Service
public class ReservationBusiness {

private final ReservationService reservationService;
	
	public ReservationBusiness(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	public String save(Reservation reservation, User user) {
		
		String messageReturn = "";
		
		try {
			messageReturn = reservationService.save(reservation);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return messageReturn;
	}

	public List<Reservation> findByHotelId(User user) {
		
		List<Reservation> reservations = null;
		
		try {
			
			reservations = reservationService.findByHotelId(user.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return reservations;
	}

	public Reservation findByHotelIdAndUuid(User user, String uuid) {
		
		Reservation reservation = null;
		
		try {
			
			reservation = reservationService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return reservation;
	}

}
