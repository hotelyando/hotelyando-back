package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.ReservationService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Reservation;
import co.com.hotelyando.database.model.User;

@Service
public class ReservationBusiness {

	private final ReservationService reservationService;
	private ServiceResponse<Reservation> serviceResponse;
	private ServiceResponses<Reservation> serviceResponses;
	private Utilities utilities = null;
	private Generic<Reservation> generic = null;

	public ReservationBusiness(ReservationService reservationService) {
		this.reservationService = reservationService;
		
		serviceResponse = new ServiceResponse<Reservation>();
		serviceResponses = new ServiceResponses<Reservation>();
		utilities = new Utilities();
		generic = new Generic<Reservation>();
		
	}
	
	public ServiceResponse<Reservation> save(Reservation reservation, User user) {
		
		String messageReturn = "";
		
		try {
			
			
			reservation.setUuid(utilities.generadorId());
			reservation.setHotelId(user.getHotelId());
			
			messageReturn = reservationService.save(reservation);
			
			serviceResponse = generic.messageReturn(reservation, PrintVariables.NEGOCIO, "Mensaje registrado!");
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}

	public ServiceResponses<Reservation> findByHotelId(User user) {
		
		List<Reservation> reservations = null;
		
		try {
			
			reservations = reservationService.findByHotelId(user.getHotelId());
			
			serviceResponses = generic.messagesReturn(reservations, PrintVariables.NEGOCIO, "Ok");
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(reservations, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}

	public ServiceResponse<Reservation> findByHotelIdAndUuid(User user, String uuid) {
		
		Reservation reservation = null;
		
		try {
			
			reservation = reservationService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			serviceResponse = generic.messageReturn(reservation, PrintVariables.NEGOCIO, "Ok");
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(reservation, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

}
