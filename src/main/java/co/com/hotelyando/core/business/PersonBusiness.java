package co.com.hotelyando.core.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.PersonService;
import co.com.hotelyando.core.services.SaleService;
import co.com.hotelyando.core.services.UserService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.model.User;

@Service
public class PersonBusiness {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SaleBusiness saleBusiness;
	
	@Autowired
	private UserService userService;
	
	private final PersonService personService;
	private ServiceResponse<Person> serviceResponse = null;
	private ServiceResponses<Person> serviceResponses = null;
	private Generic<Person> generic = null;
	private Utilities utilities = null;
	
	private String messageReturn;
	
	public PersonBusiness(PersonService personService) {
		this.personService = personService;
		
		serviceResponse = new ServiceResponse<Person>();
		serviceResponses = new ServiceResponses<Person>();
		generic = new Generic<Person>();
		utilities = new Utilities();
	}
	
	
	/*
	 * Método que registra una persona
	 * @return ServiceResponse<Person>
	 */
	public ServiceResponse<Person> save(Person person, User user) {
		
		try {
			
			person.setUuid(utilities.generadorId());
			
			messageReturn = personService.save(person);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(person, PrintVariable.NEGOCIO, messageSource.getMessage("person.register_ok", null, LocaleContextHolder.getLocale()));
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

	
	/*
	 * Método que actualiza una persona
	 * @return ServiceResponse<Person>
	 */
	public ServiceResponse<Person> update(Person person, User user) {
		
		try {
			
			messageReturn = personService.update(person);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(person, PrintVariable.NEGOCIO, messageSource.getMessage("person.update_ok", null, LocaleContextHolder.getLocale()));
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

	
	/*
	 * Método que retorna una persona por tipo y número de documento
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponse<Person> findByDocumentTypeAndDocument(String typeDocument, String documentNumber, User user) {
		
		try {
			
			Person person = personService.findByDocumentTypeAndDocument(typeDocument, documentNumber);
			
			if(person != null) {
				serviceResponse = generic.messageReturn(person, PrintVariable.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	/*
	 * Método que retorna una persona por tipo y número de documento
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponses<Person> findAll() {
		
		try {
			
			List<Person> persons = personService.findAll();
			
			if(persons != null) {
				serviceResponses = generic.messagesReturn(persons, PrintVariable.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}
	
	
	public ServiceResponse<Person> findByUuid(String uuid) {
		
		try {
			
			Person person = personService.findByUuid(uuid);
			
			if(person != null) {
				serviceResponse = generic.messageReturn(person, PrintVariable.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
		
	}
	
	
	/*
	 * Método que retorna una persona dependiendo del tipo de persona empleado o huesper
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponses<Person> findAllAndHotelIdAndSale(String nationality, String initDate, String endDate, User user) {
		
		Person person = null;
		
		List<Sale> sales = null;
		List<Person> persons = new ArrayList<Person>();
		
		try {
			
			if(nationality.equals("") && initDate.equals("") && endDate.equals("")) {
				
				persons = personService.findAll();
				
			}else if(!nationality.equals("") || !initDate.equals("") || !endDate.equals("")) {
				
				if(nationality.equals("")) {
					nationality = PrintVariable.SALE_CLIENT_ALL;
				}
				
				if(initDate.equals("")) {
					initDate = PrintVariable.INIT_DATE;
				}
				
				if(endDate.equals("")) {
					endDate = PrintVariable.END_DATE;
				}
				
				sales = saleService.findByHotelIdAndCountryAndDate(user.getHotelId(), nationality, initDate, endDate);
				
				if(sales != null) {
					
					for(int a = 0; a < sales.size(); a++) {
						for(int b = 0; b < sales.get(a).getRooms().size(); b++) {
							for(int c = 0; c < sales.get(a).getRooms().get(b).getGuests().size(); c++) {
								
								String guest = sales.get(a).getRooms().get(b).getGuests().get(c);
								
								person = new Person();
								person = personService.findByUuid(guest);
								
								if(person != null && nationality.equals(PrintVariable.SALE_CLIENT_ALL)) {
									persons.add(person);
								}
								
								if(person != null && nationality.equals(PrintVariable.SALE_CLIENT_OUTSIDE) && !person.getCountry().getName().equals(PrintVariable.SALE_LOCAL_COLOMBIA)) {
									persons.add(person);
								}
								
								if(person != null && nationality.equals(PrintVariable.SALE_CLIENT_LOCAL) && person.getCountry().getName().equals(PrintVariable.SALE_LOCAL_COLOMBIA)) {
									persons.add(person);
								}
							}
						}
					}
				}
			}
			
			if(persons != null) {
				persons = persons.stream().distinct().collect(Collectors.toList());
				serviceResponses = generic.messagesReturn(persons, PrintVariable.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}
			
			if(persons.size() <= 0) {
				serviceResponses = generic.messagesReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}
	
	
	/*
	 * Método que retorna una persona dependiendo del tipo de persona empleado o huesper
	 * @ ServiceResponse<Person>
	 */
	public ServiceResponses<Person> findPersonType(String type, User user) {
		
		List<User> users = null;
		List<Sale> sales = null;
		List<Person> persons = null;
		List<Person> persons1 = null;
		
		try {
			
			if(type.equals("EMPLOYEE")) {
				users = userService.findByHotelId(user.getHotelId());
				
				if(users != null) {
					persons = personService.findByPerson(null, users);
				}
				
			}else if(type.equals("GUEST")) {
				sales = saleService.findByHotelId(user.getHotelId());
				
				if(sales != null) {
					persons = personService.findByPerson(sales, null);
				}
				
			}else if(type.equals("ALL")){
				users = userService.findByHotelId(user.getHotelId());
				
				if(users != null) {
					persons1 = personService.findByPerson(null, users);
				}
				
				sales = saleService.findByHotelId(user.getHotelId());
				
				if(sales != null) {
					persons = personService.findByPerson(sales, null);
					
					for(int a = 0; a < persons1.size(); a++) {
						persons.add(persons1.get(a));
					}
					
					persons = persons.stream().distinct().collect(Collectors.toList());
				}
				
			}else {
				persons = personService.findAll();
			}
			

			if(persons != null) {
				serviceResponses = generic.messagesReturn(persons, PrintVariable.NEGOCIO, messageSource.getMessage("person.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.VALIDACION, messageSource.getMessage("person.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
		
	}


}
