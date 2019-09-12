package co.com.hotelyando.database.dao;

import co.com.hotelyando.database.model.Hotel;

public interface IHotelDao {
	
	String registrarHotel(Hotel hotel) throws Exception;
	Hotel consultarHotel(Integer hotelId) throws Exception;

}
