package co.com.hotelyando.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Role;

public interface IRoleRepository extends MongoRepository<Role, String>{
	
	Role findByUuid(Integer uuid);
	Role findByName(String name);

}
