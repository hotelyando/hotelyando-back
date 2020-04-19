package co.com.hotelyando.core.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.database.dao.EmployeeDao;
import co.com.hotelyando.database.model.Employee;

@Service
public class EmployeeService {
	
	private final EmployeeDao employeeDao;
	
	public EmployeeService(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	
	public String save(Employee employee) throws MongoException, Exception {
		
		String messageReturn = "";
		
		employeeDao.save(employee);
		
		return messageReturn;
	}
	
	
	public String update(Employee employee) throws MongoException, Exception {
		
		String messageReturn = "";
		
		employeeDao.save(employee);
		
		return messageReturn;
		
	}
	
	
	public List<Employee> findByUuid(String uuid) throws MongoException, Exception {
		
		Employee employee = employeeDao.findByUuid(uuid);
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		
		return employees;
	}
	
	
	public List<Employee> findByPersonId(String hotelId, String personId) throws MongoException, Exception {
		
		List<Employee> employees = employeeDao.findByPersonId(hotelId, personId);
		
		return employees;
	}
	
	
	public List<Employee> findByUserId(String hotelId, String userId) throws MongoException, Exception {
		
		List<Employee> employees = employeeDao.findByUserId(hotelId, userId);
		
		return employees;
	}
	
	
	public List<Employee> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Employee> employees = employeeDao.findByHotelId(hotelId);
		
		return employees;
	}

}
