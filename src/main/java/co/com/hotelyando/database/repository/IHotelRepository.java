package co.com.hotelyando.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Hotel;

public interface IHotelRepository extends MongoRepository<Hotel, Integer> {
	
	Hotel findByHotelId(Integer hotelId);

}
