package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.HotelService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Hotel;

@Service
public class HotelBusiness {

	
	private final HotelService hotelService;
	private ServiceResponse<Hotel> serviceResponse;
	private ServiceResponses<Hotel> serviceResponses;
	private Utilities utilities = null;
	private Generic<Hotel> generic = null;
	
	public HotelBusiness(HotelService hotelService) {
		this.hotelService = hotelService;
		
		serviceResponse = new ServiceResponse<Hotel>();
		serviceResponses = new ServiceResponses<Hotel>();
		utilities = new Utilities();
		generic = new Generic<Hotel>();
	}
	
	
	public ServiceResponse<Hotel> save(Hotel hotel) {
		
		String messageReturn = "";
		
		try {
			
			hotel.setUuid(utilities.generadorId());
			
			messageReturn = hotelService.save(hotel);
			
			serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, "Registro realizado correctamente!");
			
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}

	public ServiceResponse<Hotel> findByUuid(String uuid) {
		
		Hotel hotel = null;
		
		try {
			hotel = hotelService.findByUuid(uuid);
			
			serviceResponse = generic.messageReturn(hotel, PrintVariables.NEGOCIO, "Registro encontrado!");
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

}
