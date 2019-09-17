package co.com.hotelyando.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.IHotelDao;
import co.com.hotelyando.database.model.Hotel;

@Service
public class HotelService implements IHotelService {

	@Autowired
	private IHotelDao iHotelDao;
	
	@Override
	public String registrarHotel(Hotel hotel) {
		
		String mensajeRetorno = "";
		
		try {
			
			mensajeRetorno = iHotelDao.registrarHotel(hotel);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mensajeRetorno;
	}

	@Override
	public Hotel consultarHotel(Integer hotelId) {
		
		Hotel hotel = null;
		
		try {
			
			hotel = iHotelDao.consultarHotel(hotelId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return hotel;
	}

}
