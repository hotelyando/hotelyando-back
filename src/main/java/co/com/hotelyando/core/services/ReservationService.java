package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.ReservationDao;
import co.com.hotelyando.database.model.Reservation;

@Service
public class ReservationService {

	@Autowired
	private MessageSource messageSource;
	
	private RegularExpression regularExpression = null;
	
	private final ReservationDao reservationDao;
	
	public ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
		
		regularExpression = new RegularExpression();
	}
	
	
	/*
	 * M�todo para el registro de una reservaci�n de hotel
	 * @return String
	 */
	public String save(Reservation reservation) throws Exception {
		
		String messageReturn = "";
		
		reservationDao.save(reservation);
		
		
		//Se debe validar que la reservaci�n solicitada si este disponible.
		//En la reservaci�n se escoge la habitaci�n?
		//Como se va a mostrar la informaci�n de la habitaci�n al cliente?
		
		return messageReturn;
	}
	
	
	/*
	 * M�todo que actualiza una reservaci�n de hotel
	 * @return String
	 */
	public String update(Reservation reservation) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(reservation.getUuid())) {
			messageReturn = messageSource.getMessage("reservation.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(reservation.getHotelId())) {
			messageReturn = messageSource.getMessage("reservation.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateFormatDate(reservation.getReservationDate())) {
			messageReturn = messageSource.getMessage("reservation.date_format", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateFormatDate(reservation.getStartDate())) {
			messageReturn = messageSource.getMessage("reservation.start_date_format", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateFormatDate(reservation.getExitDate())) {
			messageReturn = messageSource.getMessage("reservation.exit_date_format", null, LocaleContextHolder.getLocale());
		}else if(reservation.getAdultQuantity() == null) {
			messageReturn = messageSource.getMessage("reservation.adult_quantity", null, LocaleContextHolder.getLocale());
		}else if(reservation.getAdultQuantity() < 1) {
			messageReturn = messageSource.getMessage("reservation.adult_quantity_number", null, LocaleContextHolder.getLocale());
		}else if(reservation.getChildrenQuantity() == null) {
			messageReturn = messageSource.getMessage("reservation.children_quantity", null, LocaleContextHolder.getLocale());
		}else if(reservation.getChildrenQuantity() < 0) {
			messageReturn = messageSource.getMessage("reservation.children_quantity_number", null, LocaleContextHolder.getLocale());
		}else if(reservation.getFullPayment() == null) {
			messageReturn = messageSource.getMessage("reservation.full_payment", null, LocaleContextHolder.getLocale());
		}else if(reservation.getAdvancedPayment() == null) {
			messageReturn = messageSource.getMessage("reservation.advanced_payment", null, LocaleContextHolder.getLocale());
		}else if(reservation.getAdvancedPayment() < 0) {
			messageReturn = messageSource.getMessage("reservation.advanced_payment_number", null, LocaleContextHolder.getLocale());
		}else if(reservation.getPerson() == null) {
			messageReturn = messageSource.getMessage("reservation.person", null, LocaleContextHolder.getLocale());
		}else {
			reservationDao.update(reservation);
		}
		
		//Se debe validar que la reservaci�n solicitada si este disponible.
		//En la reservaci�n se escoge la habitaci�n?
		//Como se va a mostrar la informaci�n de la habitaci�n al cliente?
		
		return messageReturn;
	}

	
	/*
	 * M�todo que lista todas las reservaciones de un hotel
	 * @List<Reservation>
	 */
	public List<Reservation> findByHotelId(String hotelId) throws Exception {
		
		List<Reservation> reservations = reservationDao.findByHotelId(hotelId);
		
		return reservations;
	}

	
	/*
	 * M�todo que lista una reservaci�n de un hotel por id
	 * @return Reservation
	 */
	public Reservation findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Reservation reservation = reservationDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return reservation;
	}
	
	
	public String validationData(Reservation reservation) {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(reservation.getUuid())) {
			messageReturn = messageSource.getMessage("reservation.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(reservation.getHotelId())) {
			messageReturn = messageSource.getMessage("reservation.hotel_id", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateFormatDate(reservation.getReservationDate())) {
			messageReturn = messageSource.getMessage("reservation.date_format", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateFormatDate(reservation.getStartDate())) {
			messageReturn = messageSource.getMessage("reservation.start_date_format", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateFormatDate(reservation.getExitDate())) {
			messageReturn = messageSource.getMessage("reservation.exit_date_format", null, LocaleContextHolder.getLocale());
		}else if(reservation.getAdultQuantity() == null) {
			messageReturn = messageSource.getMessage("reservation.adult_quantity", null, LocaleContextHolder.getLocale());
		}else if(reservation.getAdultQuantity() < 1) {
			messageReturn = messageSource.getMessage("reservation.adult_quantity_number", null, LocaleContextHolder.getLocale());
		}else if(reservation.getChildrenQuantity() == null) {
			messageReturn = messageSource.getMessage("reservation.children_quantity", null, LocaleContextHolder.getLocale());
		}else if(reservation.getChildrenQuantity() < 0) {
			messageReturn = messageSource.getMessage("reservation.children_quantity_number", null, LocaleContextHolder.getLocale());
		}else if(reservation.getFullPayment() == null) {
			messageReturn = messageSource.getMessage("reservation.full_payment", null, LocaleContextHolder.getLocale());
		}else if(reservation.getAdvancedPayment() == null) {
			messageReturn = messageSource.getMessage("reservation.advanced_payment", null, LocaleContextHolder.getLocale());
		}else if(reservation.getAdvancedPayment() < 0) {
			messageReturn = messageSource.getMessage("reservation.advanced_payment_number", null, LocaleContextHolder.getLocale());
		}else if(reservation.getPerson() == null) {
			messageReturn = messageSource.getMessage("reservation.person", null, LocaleContextHolder.getLocale());
		}else if(reservation.getRoomIds() == null) {
			messageReturn = messageSource.getMessage("reservation.rooms", null, LocaleContextHolder.getLocale());
		}else if(reservation.getRoomIds().size() < 1) {
			messageReturn = messageSource.getMessage("reservation.rooms", null, LocaleContextHolder.getLocale());
		}else {
			messageReturn = "";
		}
		
		return messageReturn;
		
	}

}
