package co.com.hotelyando.core.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.services.HotelService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.model.User;

@Service
public class HotelBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
	private final HotelService hotelService;
	private ServiceResponse<Hotel> serviceResponse;
	private Utilities utilities = null;
	private Generic<Hotel> generic = null;
	
	public HotelBusiness(HotelService hotelService) {
		this.hotelService = hotelService;
		
		serviceResponse = new ServiceResponse<Hotel>();
		utilities = new Utilities();
		generic = new Generic<Hotel>();
	}
	
	
	/*
	 * M�todo para el registro de hoteles
	 * @return ServiceResponse<Hotel>
	 */
	public ServiceResponse<Hotel> save(Hotel hotel) {
		
		String messageReturn = "";
		
		try {
			
			hotel.setUuid(utilities.generadorId());
			
			messageReturn = hotelService.save(hotel);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(hotel, PrintVariable.NEGOCIO, messageSource.getMessage("hotel.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				hotel.setUuid("");
				serviceResponse = generic.messageReturn(hotel, PrintVariable.VALIDACION, messageReturn);
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
	 * M�todo para la actualizaci�n de hoteles
	 * @return ServiceResponse<Hotel>
	 */
	public ServiceResponse<Hotel> update(User user, Hotel hotel) {
		
		String messageReturn = "";
		
		try {
			
			hotel.setUuid(user.getHotelId());
			
			messageReturn = hotelService.update(hotel);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(hotel, PrintVariable.NEGOCIO, messageSource.getMessage("hotel.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(hotel, PrintVariable.VALIDACION, messageReturn);
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
	 * M�todo para la b�squeda de un hotel por uuid
	 * @return ServiceResponse<Hotel>
	 */
	public ServiceResponse<Hotel> findByUuid(User user) {
		
		try {
			
			Hotel hotel = hotelService.findByUuid(user.getHotelId());
			
			if(hotel != null) {
				serviceResponse = generic.messageReturn(hotel, PrintVariable.NEGOCIO, messageSource.getMessage("hotel.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("hotel.not_content", null, LocaleContextHolder.getLocale()));
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
