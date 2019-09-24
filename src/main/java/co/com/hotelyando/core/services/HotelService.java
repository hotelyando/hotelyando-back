package co.com.hotelyando.core.services;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.HotelDao;
import co.com.hotelyando.database.model.Hotel;

@Service
public class HotelService {

	
	private final HotelDao hotelDao;
	
	public HotelService(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}
	
	
	public String registrarHotel(Hotel hotel) throws Exception {
		
		String mensajeRetorno = "";
			
		mensajeRetorno = hotelDao.registrarHotel(hotel);
		
		return mensajeRetorno;
	}

	public Hotel consultarHotel(Integer hotelId) throws Exception {
		
		Hotel hotel = null;
		hotel = hotelDao.consultarHotel(hotelId);
		
		return hotel;
	}

}
