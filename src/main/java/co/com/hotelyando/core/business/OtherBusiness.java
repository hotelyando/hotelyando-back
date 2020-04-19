package co.com.hotelyando.core.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.DataEmployee;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.services.EmployeeService;
import co.com.hotelyando.core.services.PersonService;
import co.com.hotelyando.core.services.UserService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Employee;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.User;

@Service
@Transactional
public class OtherBusiness {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;
	
	private Utilities utilities = null;
	
	public OtherBusiness() {
		
		utilities = new Utilities();
	
	}
	
	@Transactional(rollbackFor = Exception.class)
	public ServiceResponse<DataEmployee> save(DataEmployee dataEmployee, User user) {
		
		ServiceResponse<DataEmployee> serviceResponse = null;
		
		Generic<DataEmployee> generic = new Generic<DataEmployee>();
		
		String userId = "";
		String personId = "";
		
		String messageReturn = "";
		
		try {
			
			if(dataEmployee.getPerson() != null) {
				
				Person person = dataEmployee.getPerson();
				
				person.setUuid(utilities.generadorId());
				
				messageReturn = personService.save(person);
				personId = person.getUuid();
				
				if(messageReturn.equals("")) {
					
					User user1 = null;
					
					if(dataEmployee.getUser() != null) {
						
						user1 = dataEmployee.getUser();
						
						user1.setUuid(utilities.generadorId());
						user1.setHotelId(user.getHotelId());
						user1.setPersonId(person.getUuid());
						
						messageReturn = userService.save(user1);
						userId = user1.getUuid();
						
					}
					
					if(dataEmployee.getEmployee() != null) {
						
						Employee employee = dataEmployee.getEmployee();
						
						employee.setPersonId(personId);
						employee.setUuid(utilities.generadorId());
						employee.setHotelId(user.getHotelId());
						employee.setUserId(userId.equals("") ? null : userId);
						
						messageReturn = employeeService.save(employee);
						
					}else {
						messageReturn = messageSource.getMessage("other.employee", null, LocaleContextHolder.getLocale());
					}
				}
			}else {
				messageReturn = messageSource.getMessage("other.person", null, LocaleContextHolder.getLocale());
			}
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("other.employee_register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
				if(!userId.equals(""))
					userService.delete(userId);
				
				if(!personId.equals(""))
					personService.delete(personId);
				//throw new Exception(messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
}
