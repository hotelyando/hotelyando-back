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
	
	
	public String save(Hotel hotel) throws Exception {
		
		String messageReturn = "";
			
		messageReturn = hotelDao.save(hotel);
		
		return messageReturn;
	}

	public Hotel findByUuid(String uuid) throws Exception {
		
		Hotel hotel = null;
		hotel = hotelDao.findByUuid(uuid);
		
		return hotel;
	}

}
