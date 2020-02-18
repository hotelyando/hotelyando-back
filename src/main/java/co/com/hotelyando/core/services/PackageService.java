package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.PackageDao;
import co.com.hotelyando.database.model.PackageHotel;

@Service
public class PackageService {

	private PackageDao packageDao;
	
	public PackageService(PackageDao packageDao) {
		this.packageDao = packageDao;
	}
	
	
	public String save(PackageHotel packageHotel) throws Exception {
		
		String messageReturn = "";
			
		messageReturn = packageDao.save(packageHotel);
		
		return messageReturn;
	}

	public List<PackageHotel> findByHotelId(String hotelId) throws Exception {
		
		List<PackageHotel> packageHotels = null;
		packageHotels = packageDao.findByHotelId(hotelId);
		
		return packageHotels;
	}

	public PackageHotel findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		PackageHotel packageHotel = null;
		packageHotel = packageDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return packageHotel;
	}

}
