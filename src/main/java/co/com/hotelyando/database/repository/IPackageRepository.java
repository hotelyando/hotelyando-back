package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.PackageHotel;

@JaversSpringDataAuditable
public interface IPackageRepository extends MongoRepository<PackageHotel, String> {
	
	List<PackageHotel> findByHotelId(String hotelId);
	PackageHotel findByHotelIdAndUuid(String hotelId, String uuid);
	
}