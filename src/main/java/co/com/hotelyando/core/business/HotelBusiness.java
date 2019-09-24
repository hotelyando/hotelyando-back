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
	
	
	public String registrarHotel(Hotel hotel) {
		
		String mensajeRetorno = "";
		
		try {
			mensajeRetorno = hotelService.registrarHotel(hotel);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return mensajeRetorno;
	}

	public Hotel consultarHotel(Integer hotelId) {
		
		Hotel hotel = null;
		
		try {
			hotel = hotelService.consultarHotel(hotelId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return hotel;
	}

}
