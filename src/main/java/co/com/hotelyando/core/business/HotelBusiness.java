package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.HotelService;
import co.com.hotelyando.database.model.Hotel;

@Service
public class HotelBusiness {

	
	private final HotelService hotelService;
	
	public HotelBusiness(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	
	public String save(Hotel hotel) {
		
		String messageReturn = "";
		
		try {
			messageReturn = hotelService.save(hotel);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return messageReturn;
	}

	public Hotel findByUuid(String uuid) {
		
		Hotel hotel = null;
		
		try {
			hotel = hotelService.findByUuid(uuid);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return hotel;
	}

}
