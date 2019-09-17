package co.com.hotelyando.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.repository.IHotelRepository;

@Repository
public class HotelDao implements IHotelDao {

	@Autowired
	private IHotelRepository iHotelRepository;
	
	@Override
	public String registrarHotel(Hotel hotel) throws Exception {
		
		iHotelRepository.save(hotel);
		
		return null;
	}

	@Override
	public Hotel consultarHotel(Integer hotelId) throws Exception {
		
		Hotel hotel = null;
		
		hotel = iHotelRepository.findByHotelId(hotelId);
		
		return hotel;
	}

}
