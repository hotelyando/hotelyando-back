package co.com.hotelyando.database.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Role;

@JaversSpringDataAuditable
public interface IRoleRepository extends MongoRepository<Role, String>{
	
	Role findByUuid(Integer uuid);
	Role findByName(String name);

}
