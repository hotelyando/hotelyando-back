package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Employee;

@JaversSpringDataAuditable
public interface IEmployeeRepository extends MongoRepository<Employee, String>{
	
	Employee findByUuid(String uuid);
	List<Employee> findByHotelId(String hotelId);
	List<Employee> findByHotelIdAndPersonId(String hotelId, String personId);
	List<Employee> findByHotelIdAndUserId(String hotelId, String userId);
	
}
