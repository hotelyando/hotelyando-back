package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.ReservationService;
import co.com.hotelyando.core.services.RoomService;
import co.com.hotelyando.core.services.RoomTypeService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Reservation;
import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.model.RoomType;
import co.com.hotelyando.database.model.User;
import lombok.val;

@Service
public class ReservationBusiness {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	private Room room;
	private RoomType roomType;
	
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
	
	
	/*
	 * M�todo para el registro de una reservaci�n de hotel
	 * @return String
	 */
	public ServiceResponse<Reservation> save(Reservation reservation, User user) {
		
		String messageReturn = "";
		
		try {
			
			
			reservation.setUuid(utilities.generadorId());
			reservation.setHotelId(user.getHotelId());
			
			messageReturn = reservationService.validationData(reservation);
			
			if(messageReturn.equals("")) {
				
				//reservation.getRoomIds().forEach((value) -> {
				for(int a = 0; a < reservation.getRoomIds().size(); a++) {
					
					try {
						room = roomService.findByHotelIdAndUuid(user.getHotelId(), reservation.getRoomIds().get(a));
						
						roomType = roomTypeService.findByHotelIdAndRoomType(user.getHotelId(), room.getRoomType());
						
						Double price = 0.0;
						price = roomType.getPriceDay() + price;
						
						reservation.getValues().setDiscount(0.0);
						reservation.getValues().setGross(0);
						reservation.getValues().setTax(0.0);
						reservation.getValues().setTotal(price);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				messageReturn = reservationService.save(reservation);
				
			}
			
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(reservation, PrintVariable.NEGOCIO, messageSource.getMessage("reservation.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				reservation.setUuid("");
				serviceResponse = generic.messageReturn(reservation, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}
	
	
	/*
	 * M�todo que actualiza una reservaci�n de hotel
	 * @return String
	 */
	public ServiceResponse<Reservation> update(Reservation reservation, User user) {
		
		String messageReturn = "";
		
		try {
			
			reservation.setHotelId(user.getHotelId());
			
			messageReturn = reservationService.update(reservation);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(reservation, PrintVariable.NEGOCIO, messageSource.getMessage("reservation.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(reservation, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}

	
	/*
	 * M�todo que lista todas las reservaciones de un hotel
	 * @List<Reservation>
	 */
	public ServiceResponses<Reservation> findByHotelId(User user) {
		
		try {
			
			List<Reservation> reservations = reservationService.findByHotelId(user.getHotelId());
			
			if(reservations != null) {
				serviceResponses = generic.messagesReturn(reservations, PrintVariable.NEGOCIO, messageSource.getMessage("reservation.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("reservation.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
			
		return serviceResponses;
	}

	
	/*
	 * M�todo que lista una reservaci�n de un hotel por id
	 * @return Reservation
	 */
	public ServiceResponse<Reservation> findByHotelIdAndUuid(User user, String uuid) {
		
		try {
			
			Reservation reservation = reservationService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(reservation != null) {
				serviceResponse = generic.messageReturn(reservation, PrintVariable.NEGOCIO, messageSource.getMessage("reservation.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("reservation.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
		
		return serviceResponse;
	}

}
