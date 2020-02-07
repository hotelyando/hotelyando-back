package co.com.hotelyando.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Rol;

public interface IRolRepository extends MongoRepository<Rol, Integer>{
	
	Rol findByRolId(Integer rolId);
	Rol findByNombre(String nombre);

}
