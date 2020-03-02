package co.com.hotelyando.database.dao;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Hotel;
import co.com.hotelyando.database.repository.IHotelRepository;

@Repository
public class HotelDao {

	private final IHotelRepository iHotelRepository;
	
	public HotelDao(IHotelRepository iHotelRepository) {
		this.iHotelRepository = iHotelRepository;
	}
	
	
	public void save(Hotel hotel) throws MongoException, Exception {
		iHotelRepository.save(hotel);
	}

	public void update(Hotel hotel) throws MongoException, Exception {
		iHotelRepository.save(hotel);
	}
	
	public Hotel findByUuid(String uuid) throws MongoException, Exception {
		
		Hotel hotel = null;
		hotel = iHotelRepository.findByUuid(uuid);
		
		return hotel;
	}
	
	public Hotel findByName(String name) throws MongoException, Exception {
		
		Hotel hotel = null;
		hotel = iHotelRepository.findByName(name);
		
		return hotel;
	}
	
	public Hotel findByNit(String nit) throws MongoException, Exception {
		
		Hotel hotel = null;
		hotel = iHotelRepository.findByNit(nit);
		
		return hotel;
	}

}
