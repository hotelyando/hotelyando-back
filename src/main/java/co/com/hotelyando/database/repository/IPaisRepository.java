package co.com.hotelyando.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Pais;

public interface IPaisRepository extends MongoRepository<Pais, Integer> {

}
