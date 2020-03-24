package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.PackageHotel;
import co.com.hotelyando.database.repository.IPackageRepository;

@Repository
public class PackageDao{
	
	private final IPackageRepository iPackageRepository;
	
	public PackageDao(IPackageRepository iPackageRepository) {
		this.iPackageRepository = iPackageRepository;
	}
	
	public String save(PackageHotel packageHotel) throws Exception {
		
		iPackageRepository.save(packageHotel);
		
		return "Ok";
	}

	public List<PackageHotel> findByHotelId(String hotelId) throws Exception {
		
		List<PackageHotel> packageHotels = iPackageRepository.findByHotelId(hotelId);
		
		return packageHotels;
	}

	public PackageHotel findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		PackageHotel packageHotel = iPackageRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return packageHotel;
	}

}
