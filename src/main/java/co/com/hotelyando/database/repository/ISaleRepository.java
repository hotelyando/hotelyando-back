package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Sale;

@JaversSpringDataAuditable
public interface ISaleRepository extends MongoRepository<Sale, String> {
	
	List<Sale> findByHotelId(String hotelId);
	Sale findByHotelIdAndUuid(String hotelId, String uuid);
	List<Sale> findByHotelIdAndState(String hotelId, String state);
	List<Sale> findByHotelIdAndDateBetween(String hotelId, String from, String to);
	
	
}
