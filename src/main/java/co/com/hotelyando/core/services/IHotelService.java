package co.com.hotelyando.core.services;

import co.com.hotelyando.database.model.Hotel;

public interface IHotelService {
	
	String registrarHotel(Hotel hotel);
	Hotel consultarHotel(Integer hotelId);

}
