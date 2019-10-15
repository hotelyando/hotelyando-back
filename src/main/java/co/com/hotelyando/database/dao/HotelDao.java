package co.com.hotelyando.database.dao;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.repository.IHotelRepository;

@Repository
public class HotelDao {

	private final IHotelRepository iHotelRepository;
	
	public HotelDao(IHotelRepository iHotelRepository) {
		this.iHotelRepository = iHotelRepository;
	}
	
	
	public String registrarHotel(Hotel hotel) throws Exception {
		
		iHotelRepository.save(hotel);
		
		return "Ok";
	}

	public Hotel consultarHotel(Integer hotelId) throws Exception {
		
		Hotel hotel = null;
		hotel = iHotelRepository.findByHotelId(hotelId);
		
		return hotel;
	}

}
