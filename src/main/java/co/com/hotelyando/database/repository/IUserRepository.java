package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.User;

@JaversSpringDataAuditable
public interface IUserRepository extends MongoRepository<User, String> {
	
	List<User> findByHotelId(String hotelId);
	User findByHotelIdAndUuid(String hotelId, String uuid);
	User findByUserAndPassword(String user, String password);

}
