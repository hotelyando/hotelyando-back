package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PackageService;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.PackageHotel;
import co.com.hotelyando.database.model.User;

@Service
public class PackageBusiness {

	private final PackageService packageService;
	private Utilities utilities = null;
	
	public PackageBusiness(PackageService packageService) {
		this.packageService = packageService;
		
		utilities = new Utilities();
	}
	
	public String save(PackageHotel packageHotel, User user) {
		
		String messageReturn = "";
		
		try {
			
			packageHotel.setUuid(utilities.generadorId());
			packageHotel.setHotelId(user.getHotelId());
			
			messageReturn = packageService.save(packageHotel);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return messageReturn;
	}

	public List<PackageHotel> findByHotelId(User user) {
		
		List<PackageHotel> packageHotels = null;
		
		try {
			
			packageHotels = packageService.findByHotelId(user.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return packageHotels;
	}

	public PackageHotel findByHotelIdAndUuid(User user, String uuid) {
		
		PackageHotel packageHotel = null;
		
		try {
			
			packageHotel = packageService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return packageHotel;
	}


}
