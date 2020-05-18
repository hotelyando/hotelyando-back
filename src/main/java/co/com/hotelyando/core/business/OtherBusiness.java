package co.com.hotelyando.core.business;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.DataEmployee;
import co.com.hotelyando.core.model.LiquidateItem;
import co.com.hotelyando.core.model.LiquidateItem.QuantityItem;
import co.com.hotelyando.core.model.LiquidateRoom;
import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.services.EmployeeService;
import co.com.hotelyando.core.services.ItemService;
import co.com.hotelyando.core.services.PersonService;
import co.com.hotelyando.core.services.RoomService;
import co.com.hotelyando.core.services.RoomTypeService;
import co.com.hotelyando.core.services.UserService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Employee;
import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.model.RoomType;
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
	
	@Autowired
	private RoomService roomService; 
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private ItemService itemService;
	
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
				serviceResponse = generic.messageReturn(dataEmployee, PrintVariable.NEGOCIO, messageSource.getMessage("other.employee_register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(dataEmployee, PrintVariable.VALIDACION, messageReturn);
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
	
	
	
	public ServiceResponse<LiquidateRoom> liquidateRoom(User user, LiquidateRoom liquidateRoom) {
		
		ServiceResponse<LiquidateRoom> serviceResponse = null;
		
		Generic<LiquidateRoom> generic = new Generic<LiquidateRoom>();
		
		String messageReturn = "";
		
		Double price = 0.0;
		
		Room room = null;
		RoomType roomType = null;
		
		try {
			
			
			for(int a = 0; a < liquidateRoom.getRoomsUuid().size(); a++) {
				
				try {
					room = roomService.findByHotelIdAndUuid(user.getHotelId(), liquidateRoom.getRoomsUuid().get(a));
					
					roomType = roomTypeService.findByHotelIdAndRoomType(user.getHotelId(), room.getRoomTypeUuid());
						
					LocalDateTime starDate = LocalDateTime.parse(liquidateRoom.getInitDate());
					LocalDateTime exitDate = LocalDateTime.parse(liquidateRoom.getEndDate());
					
					long duration = Duration.between(starDate, exitDate).toDays();
					
					for(int days = 0; days < duration; days++) {
						
						if(roomType.getPriceDetails() != null && roomType.getPriceDetails().size() > 0) {
							
							boolean foundDay = true;
							
							for(int b = 0; b < roomType.getPriceDetails().size(); b++) {
								
								LocalDateTime dateTime = starDate.plusDays(days);
								String day = dateTime.getDayOfWeek().toString();
									
								if(day.equals(roomType.getPriceDetails().get(b).getDay())) {
									price = roomType.getPriceDetails().get(b).getPriceDay() + price;
									foundDay = false;
									break;
								}
							}
							
							if(foundDay) {
								price = roomType.getPriceDay() + price;
							}
						}else {
							price = roomType.getPriceDay() + price;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			liquidateRoom.setDiscount(0.0);
			liquidateRoom.setTax(0.19);
			liquidateRoom.setTotal(price);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(liquidateRoom, PrintVariable.NEGOCIO, messageSource.getMessage("other.liquidate_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(liquidateRoom, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	public ServiceResponse<LiquidateItem> liquidateItem(User user, LiquidateItem liquidateItem) {
		
		ServiceResponse<LiquidateItem> serviceResponse = null;
		
		Generic<LiquidateItem> generic = new Generic<LiquidateItem>();
		
		String messageReturn = "";
		
		Double price = 0.0;
		
		Item item = null;
		List<QuantityItem> quantityItems = null;
		
		
		try {
			
			quantityItems = liquidateItem.getItems();
			
			for(int a = 0; a < quantityItems.size(); a++) {
				
				item = itemService.findByHotelIdAndUuid(user.getHotelId(), quantityItems.get(a).getItemUuid());
					
				if(item.getQuantity() > liquidateItem.getItems().get(a).getQuantity()) {
					
					price = (item.getPrice() * item.getQuantity()) + price;
					
				}else {
					messageReturn = "La cantidad ingresada de " + item.getName() + ", no se encuetra en stock.";
				}
			}
			
			liquidateItem.setTax(0.19);
			liquidateItem.setDiscount(0.0);
			liquidateItem.setTotal(price);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(liquidateItem, PrintVariable.NEGOCIO, messageSource.getMessage("other.liquidate_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(liquidateItem, PrintVariable.VALIDACION, messageReturn);
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
