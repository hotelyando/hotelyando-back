package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.EmployeeService;
import co.com.hotelyando.core.services.PersonService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Employee;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.User;

@Service
public class EmployeeBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PersonService personService;
	
	private final EmployeeService employeeService;
	
	private ServiceResponse<Employee> serviceResponse;
	private ServiceResponses<Employee> serviceResponses;
	
	private Utilities utilities = null;
	private Generic<Employee> generic = null;
	
	private String messageReturn;
	
	public EmployeeBusiness(EmployeeService employeeService) {
		this.employeeService = employeeService;
		
		serviceResponse = new ServiceResponse<Employee>();
		serviceResponses = new ServiceResponses<Employee>();
		utilities = new Utilities();
		generic = new Generic<Employee>();
	}
	
	
	public ServiceResponse<Employee> save(Employee employee, User user) {
		
		try {
			
			employee.setUuid(utilities.generadorId());
			employee.setHotelId(user.getHotelId());
			
			if(employee.getPersonId().equals("")) {
				messageReturn = messageSource.getMessage("employee.person", null, LocaleContextHolder.getLocale());
			}else {
				
				Person person = personService.findByUuid(employee.getPersonId());
				
				if(person == null) {
					messageReturn = messageSource.getMessage("employee.person_not_content", null, LocaleContextHolder.getLocale());
				}
			}
			
			if(messageReturn.equals("")) {
				messageReturn = employeeService.save(employee);
			}
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(employee, PrintVariable.NEGOCIO, messageSource.getMessage("employee.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	public ServiceResponse<Employee> update(Employee employee, User user) {
		
		try {
			
			employee.setUuid(utilities.generadorId());
			employee.setHotelId(user.getHotelId());
			
			if(employee.getPersonId().equals("")) {
				messageReturn = messageSource.getMessage("employee.person", null, LocaleContextHolder.getLocale());
			}else {
				
				Person person = personService.findByUuid(employee.getPersonId());
				
				if(person == null) {
					messageReturn = messageSource.getMessage("employee.person_not_content", null, LocaleContextHolder.getLocale());
				}
			}
			
			if(messageReturn.equals("")) {
				messageReturn = employeeService.update(employee);
			}
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(employee, PrintVariable.NEGOCIO, messageSource.getMessage("employee.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	
	
	public ServiceResponses<Employee> findByUuid(String uuid) {
		
		try {
			
			List<Employee> employees = employeeService.findByUuid(uuid);
			
			if(employees != null) {
				serviceResponses = generic.messagesReturn(employees, PrintVariable.NEGOCIO, messageSource.getMessage("employee.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("employee.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
	}
	
	
	public ServiceResponses<Employee> findByPersonId(User user, String personId) {
		
		try {
			
			List<Employee> employees = employeeService.findByPersonId(user.getHotelId(), personId);
			
			if(employees != null) {
				serviceResponses = generic.messagesReturn(employees, PrintVariable.NEGOCIO, messageSource.getMessage("employee.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("employee.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
	}
	
	
	public ServiceResponses<Employee> findByUserId(User user, String userId) {
		
		try {
			
			List<Employee> employees = employeeService.findByUserId(user.getHotelId(), userId);
			
			if(employees != null) {
				serviceResponses = generic.messagesReturn(employees, PrintVariable.NEGOCIO, messageSource.getMessage("employee.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("employee.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}
	
	
	public ServiceResponses<Employee> findByHotelId(User user) {
		
		try {
			
			List<Employee> employees = employeeService.findByHotelId(user.getHotelId());
			
			if(employees != null) {
				serviceResponses = generic.messagesReturn(employees, PrintVariable.NEGOCIO, messageSource.getMessage("employee.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("employee.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
	}

}
