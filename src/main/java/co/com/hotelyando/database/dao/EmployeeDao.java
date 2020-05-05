package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Employee;
import co.com.hotelyando.database.repository.IEmployeeRepository;

@Repository
public class EmployeeDao {
	
	private final IEmployeeRepository iEmployeeRepository;
	
	public EmployeeDao(IEmployeeRepository iEmployeeRepository) {
		this.iEmployeeRepository = iEmployeeRepository;
	}

	
	public void save(Employee employee) throws MongoException, Exception {
		iEmployeeRepository.save(employee);
	}
	
	
	public void update(Employee employee) throws MongoException, Exception {
		iEmployeeRepository.save(employee);
	}
	
	
	public void delete(String uuid) throws MongoException, Exception {
		iEmployeeRepository.deleteById(uuid);
	}
	
	public Employee findByUuid(String uuid) throws MongoException, Exception {
		
		Employee employee = iEmployeeRepository.findByUuid(uuid);
		
		return employee;
	}
	
	
	public List<Employee> findByPersonId(String hotelId, String personId) throws MongoException, Exception {
		
		List<Employee> employees = iEmployeeRepository.findByHotelIdAndPersonId(hotelId, personId);
		
		return employees;
	}
	
	
	public List<Employee> findByUserId(String hotelId, String userId) throws MongoException, Exception {
		
		List<Employee> employees = iEmployeeRepository.findByHotelIdAndUserId(hotelId, userId);
		
		return employees;
	}
	
	
	public List<Employee> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Employee> employees = iEmployeeRepository.findByHotelId(hotelId);
		
		return employees;
	}
	
}
