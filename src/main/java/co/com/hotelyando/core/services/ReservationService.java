package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.PrintEntity;
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
			
		if(StringUtils.isBlank(reservation.getUuid())) {
			messageReturn = PrintEntity.RESERVATION_ID;
		}else if(StringUtils.isBlank(reservation.getHotelId())) {
			messageReturn = PrintEntity.RESERVATION_HOTEL_ID;
		}else if(StringUtils.isBlank(reservation.getReservationDate())) {
			messageReturn = PrintEntity.RESERVATION_DATE;
		}else if(StringUtils.isBlank(reservation.getStartDate())) {
			messageReturn = PrintEntity.RESERVATION_START_DATE;
		}else if(StringUtils.isBlank(reservation.getExitDate())) {
			messageReturn = PrintEntity.RESERVATION_EXIT_DATE;
		}else if(reservation.getAdultQuantity() == null) {
			messageReturn = PrintEntity.RESERVATION_ADULT_QUANTITY;
		}else if(reservation.getChildrenQuantity() == null) {
			messageReturn = PrintEntity.RESERVATION_CHILDREN_QUANTITY;
		}else if(reservation.getFullPayment() == null) {
			messageReturn = PrintEntity.RESERVATION_FULL_PAYMENT;
		}else if(reservation.getAdvancedPayment() == null) {
			messageReturn = PrintEntity.RESERVATION_ADVANCED_PAYMENT;
		}else if(reservation.getPerson() == null) {
			messageReturn = PrintEntity.RESERVATION_PERSON;
		}else {
			reservationDao.save(reservation);
		}
		
		//Se debe validar que la reservación solicitada si este disponible.
		//En la reservación se escoge la habitación?
		//Como se va a mostrar la información de la habitación al cliente?
		
		return messageReturn;
	}
	
	public String update(Reservation reservation) throws Exception {
		
		String messageReturn = "";
			
		if(StringUtils.isBlank(reservation.getUuid())) {
			messageReturn = PrintEntity.RESERVATION_ID;
		}else if(StringUtils.isBlank(reservation.getHotelId())) {
			messageReturn = PrintEntity.RESERVATION_HOTEL_ID;
		}else if(StringUtils.isBlank(reservation.getReservationDate())) {
			messageReturn = PrintEntity.RESERVATION_DATE;
		}else if(StringUtils.isBlank(reservation.getStartDate())) {
			messageReturn = PrintEntity.RESERVATION_START_DATE;
		}else if(StringUtils.isBlank(reservation.getExitDate())) {
			messageReturn = PrintEntity.RESERVATION_EXIT_DATE;
		}else if(reservation.getAdultQuantity() == null) {
			messageReturn = PrintEntity.RESERVATION_ADULT_QUANTITY;
		}else if(reservation.getChildrenQuantity() == null) {
			messageReturn = PrintEntity.RESERVATION_CHILDREN_QUANTITY;
		}else if(reservation.getFullPayment() == null) {
			messageReturn = PrintEntity.RESERVATION_FULL_PAYMENT;
		}else if(reservation.getAdvancedPayment() == null) {
			messageReturn = PrintEntity.RESERVATION_ADVANCED_PAYMENT;
		}else if(reservation.getPerson() == null) {
			messageReturn = PrintEntity.RESERVATION_PERSON;
		}else {
			reservationDao.update(reservation);
		}
		
		//Se debe validar que la reservación solicitada si este disponible.
		//En la reservación se escoge la habitación?
		//Como se va a mostrar la información de la habitación al cliente?
		
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
