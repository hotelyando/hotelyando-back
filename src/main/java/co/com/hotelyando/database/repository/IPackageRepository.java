package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.PackageHotel;

public interface IPackageRepository extends MongoRepository<PackageHotel, String> {
	
	List<PackageHotel> findByHotelId(String hotelId);
	PackageHotel findByHotelIdAndUuid(String hotelId, String uuid);
	
}