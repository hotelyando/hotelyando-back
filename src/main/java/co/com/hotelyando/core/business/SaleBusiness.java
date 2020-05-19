package co.com.hotelyando.core.business;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.ItemService;
import co.com.hotelyando.core.services.PersonService;
import co.com.hotelyando.core.services.RoomService;
import co.com.hotelyando.core.services.RoomTypeService;
import co.com.hotelyando.core.services.SaleService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.model.RoomType;
import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.model.Sale.Values;
import co.com.hotelyando.database.model.User;

@Service
public class SaleBusiness {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	
	private final SaleService saleService;
	private ServiceResponse<Sale> serviceResponse;
	private ServiceResponses<Sale> serviceResponses;
	private Generic<Sale> generic = null;
	private Utilities utilities;
	
	public SaleBusiness(SaleService saleService) {
		this.saleService = saleService;
		
		serviceResponse = new ServiceResponse<Sale>();
		serviceResponses = new ServiceResponses<Sale>();
		generic = new Generic<Sale>();
		utilities = new Utilities();
		
	}
	
	public ServiceResponse<Sale> save(Sale sale, User user) {
		
		String messageReturn = "";
		
		try {
			
			sale.setHotelId(user.getHotelId());
			sale.setUuid(utilities.generadorId());
			
			messageReturn = saleService.save(sale);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(sale, PrintVariable.NEGOCIO, messageSource.getMessage("sale.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				sale.setUuid("");
				serviceResponse = generic.messageReturn(sale, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}
	
	public ServiceResponse<Sale> update(Sale sale, User user) {
		
		String messageReturn = "";
		
		Item item = null;
		Room room = null;
		RoomType roomType = null;
		
		Double price = 0.0;
		
		Values values = null;
		
		try {
			
			sale.setHotelId(user.getHotelId());
			
			messageReturn = saleService.validateSale(sale);
			
			if(messageReturn.equals("")) {
				
				//Calculamos los items si los hay
				messageReturn = saleService.validaSaleItem(sale);
				
				if(messageReturn.equals("") && sale.getItems() != null) {
					
					for(int a = 0; a < sale.getItems().size(); a++) {
						
						item = itemService.findByHotelIdAndUuid(sale.getHotelId(), sale.getItems().get(a).getUuid());
						
						if(item.getStock() > sale.getItems().get(a).getQuantity()) {
							price = (item.getPrice() * sale.getItems().get(a).getQuantity()) + price;
							
							values = new Values();
							values.setDiscount(0.0);
							values.setGross(0.0);
							values.setNet(0.0);
							values.setTax(0.0);
							values.setTotal(price);
							
							sale.getItems().get(a).setValues(values);
							
						}
					}
				}
				
				
				messageReturn = saleService.validateRoom(sale);
				
				if(messageReturn.equals("") && sale.getRooms() != null) {
					
					price = 0.0;
					
					for(int a = 0; a < sale.getRooms().size(); a++) {
						
						
						room = roomService.findByHotelIdAndUuid(user.getHotelId(), sale.getRooms().get(a).getUuid());
							
						roomType = roomTypeService.findByHotelIdAndRoomType(user.getHotelId(), room.getRoomTypeUuid());
								
						LocalDateTime starDate = LocalDateTime.parse(sale.getRooms().get(a).getStartDate());
						LocalDateTime exitDate = LocalDateTime.parse(sale.getRooms().get(a).getEndDate());
							
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
						
						values = new Values();
						values.setDiscount(0.0);
						values.setGross(0.0);
						values.setNet(0.0);
						values.setTax(0.19);
						values.setTotal(price);
						
						sale.getRooms().get(a).setValues(values);
						
					}
				}
				
				messageReturn = saleService.update(sale);
				
			}
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(sale, PrintVariable.NEGOCIO, messageSource.getMessage("sale.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(sale, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}

	public ServiceResponses<Sale> findByHotelId(User user) {
		
		try {
			
			List<Sale> sales = saleService.findByHotelId(user.getHotelId());
			
			if(sales == null) {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("sale.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(sales, PrintVariable.NOT_CONTENT, messageSource.getMessage("sale.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}
	
	
	public ServiceResponses<Sale> findByHotelIdAndCountryAndDate(String personId, String nationality, String initDate, String endDate, User user) {
		
		Person person = null;
		List<Sale> returnSales = null;
		List<Sale> sales = null;
		
		try {
			
			if(personId.equals("")) {
				if(nationality.equals("") && initDate.equals("") && endDate.equals("")) {
					
					returnSales = saleService.findByHotelId(user.getHotelId());
					
				}else if(!nationality.equals("") || !initDate.equals("") || !endDate.equals("")) {
					
					nationality = nationality.equals("") ? PrintVariable.SALE_CLIENT_ALL : nationality;
					initDate = initDate.equals("") ? PrintVariable.INIT_DATE : initDate;
					endDate = endDate.equals("") ? PrintVariable.END_DATE : endDate;
					
					sales = saleService.findByHotelIdAndCountryAndDate(user.getHotelId(), nationality, initDate, endDate);
					
					returnSales = new ArrayList<Sale>();
					
					for(int a = 0; a < sales.size(); a++) {
						
						String guest = sales.get(a).getClient().getUuid();
						
						person = new Person();
						person = personService.findByUuid(guest);
						
						if(person != null && nationality.equals(PrintVariable.SALE_CLIENT_ALL)) {
							returnSales.add(sales.get(a));
						}
						
						if(person != null && nationality.equals(PrintVariable.SALE_CLIENT_OUTSIDE) && !person.getCountry().getName().equals(PrintVariable.SALE_LOCAL_COLOMBIA)) {
							returnSales.add(sales.get(a));
						}
						
						if(person != null && nationality.equals(PrintVariable.SALE_CLIENT_LOCAL) && person.getCountry().getName().equals(PrintVariable.SALE_LOCAL_COLOMBIA)) {
							returnSales.add(sales.get(a));
						}
					}
				}
			}else {
				returnSales = saleService.findByHotelIdAndPersonId(user.getHotelId(), personId);
			}
			
			if(returnSales != null) {
				serviceResponses = generic.messagesReturn(returnSales, PrintVariable.NEGOCIO, messageSource.getMessage("sale.find_ok", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}

	public ServiceResponse<Sale> findByHotelIdAndUuid(User user, String uuid) {
		
		try {
			
			Sale sale = saleService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(sale != null) {
				serviceResponse = generic.messageReturn(sale, PrintVariable.NEGOCIO, "");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.NOT_CONTENT, messageSource.getMessage("sale.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

}
