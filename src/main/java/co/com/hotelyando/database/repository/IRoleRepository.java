package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Role;

@JaversSpringDataAuditable
public interface IRoleRepository extends MongoRepository<Role, String>{
	
	Role findByUuid(Integer uuid);
	List<Role> findByHotelId(String hotelId);
	Role findByName(String hotelId, String name);
	Role findByName(String name);

}
