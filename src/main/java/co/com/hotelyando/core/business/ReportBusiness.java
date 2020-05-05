package co.com.hotelyando.core.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.SaleReport;
import co.com.hotelyando.core.model.SaleReport.ClientSale;
import co.com.hotelyando.core.model.SaleReport.ItemSale;
import co.com.hotelyando.core.model.SaleReport.RoomSale;
import co.com.hotelyando.core.model.SaleReport.Values;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.PersonService;
import co.com.hotelyando.core.services.SaleService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.model.User;

@Service
public class ReportBusiness {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private PersonService personService;
	
	private ItemSale itemSale;
	private Values values;
	private RoomSale roomSale;
	private ClientSale clientSale;
	
	private Person person;
	
	private Sale sale = null;
	private SaleReport saleReport = null;
	
	
	private List<Sale> sales = null;
	private List<SaleReport> saleReports = null;
	
	private List<ItemSale> itemSales = null;
	private List<RoomSale> roomSales = null;
	private List<ClientSale> clientSales = null;
	
	private Generic<SaleReport> generic = null;
	
	private ServiceResponses<SaleReport> serviceResponses;
	
	
	public ReportBusiness() {
		generic = new Generic<SaleReport>();
	}
	
	public ServiceResponses<SaleReport> findBySaleReport(String personId, String nationality, String initDate, String endDate, User user) {
		
		try {
			
			sales = findByHotelIdAndCountryAndDate(personId, nationality, initDate, endDate, user);
			
			saleReports = new ArrayList<SaleReport>();
			
			if(sales != null) {
				
				for(int a = 0; a < sales.size(); a++) {
					
					sale = new Sale();
					sale = sales.get(a);
					
					saleReport = new SaleReport();
					saleReport.setUuid(sale.getUuid());
					saleReport.setHotelId(sale.getHotelId());
					saleReport.setDate(sale.getDate());
					saleReport.setState(sale.getState());
					
					values = new Values();
					values.setDiscount(sale.getValues().getDiscount());
					values.setGross(sale.getValues().getGross());
					values.setNet(sale.getValues().getNet());
					values.setTax(sale.getValues().getTax());
					values.setTotal(sale.getValues().getTotal());
					
					saleReport.setValues(values);
					
					itemSales = new ArrayList<SaleReport.ItemSale>();
					sale.getItems().forEach((item) ->{
						
						itemSale = new ItemSale();
						itemSale.setDateSale(item.getDateSale());
						itemSale.setDescription(item.getDescription());
						itemSale.setQuantity(item.getQuantity());
						itemSale.setUuid(item.getUuid());
						
						values = new Values();
						values.setDiscount(item.getValues().getDiscount());
						values.setGross(item.getValues().getGross());
						values.setNet(item.getValues().getNet());
						values.setTax(item.getValues().getTax());
						values.setTotal(item.getValues().getTotal());
						
						itemSale.setValues(values);
											
						itemSales.add(itemSale);
					});
					
					saleReport.setItems(itemSales);
					
					roomSales = new ArrayList<SaleReport.RoomSale>();
					sale.getRooms().forEach((room) -> {
						
						
						roomSale = new RoomSale();
						roomSale.setDescription(room.getDescription());
						roomSale.setEndDate(room.getEndDate());
						roomSale.setStartDate(room.getStartDate());
						roomSale.setUuid(room.getUuid());
						
						values = new Values();
						values.setDiscount(room.getValues().getDiscount());
						values.setGross(room.getValues().getGross());
						values.setNet(room.getValues().getNet());
						values.setTax(room.getValues().getTax());
						values.setTotal(room.getValues().getTotal());
						
						roomSale.setValues(values);
						
						clientSales = new ArrayList<SaleReport.ClientSale>();
						room.getGuests().forEach((guest) -> {
							
							try {
								person = personService.findByUuid(guest);
							}catch (Exception e) {
								 person = null;
							}
							
							if(person != null) {
								clientSale = new ClientSale();
								clientSale.setAddress(person.getAddress());
								clientSale.setBirthdate(person.getBirthdate());
								clientSale.setCellPhone(person.getCellPhone());
								clientSale.setDocument(person.getDocument());
								clientSale.setDocumentType(person.getDocumentType());
								clientSale.setEmail(person.getEmail());
								clientSale.setFirstName(person.getFirstName());
								clientSale.setLastName(person.getLastName());
								clientSale.setPhone(person.getPhone());
								clientSale.setUuid(person.getUuid());
								clientSale.setCountry(person.getCountry());
								clientSales.add(clientSale);
							}
							
						});
						
						roomSale.setGuests(clientSales);
						
						roomSales.add(roomSale);
						
					});
					
					saleReport.setRooms(roomSales);
					
					person = new Person();
					person = personService.findByUuid(sale.getClient().getUuid());
					
					clientSale = new ClientSale();
					clientSale.setAddress(person.getAddress());
					clientSale.setBirthdate(person.getBirthdate());
					clientSale.setCellPhone(person.getCellPhone());
					clientSale.setDocument(person.getDocument());
					clientSale.setDocumentType(person.getDocumentType());
					clientSale.setEmail(person.getEmail());
					clientSale.setFirstName(person.getFirstName());
					clientSale.setLastName(person.getLastName());
					clientSale.setPhone(person.getPhone());
					clientSale.setUuid(person.getUuid());
					clientSale.setCountry(person.getCountry());
					
					saleReport.setClient(clientSale);
					
					saleReports.add(saleReport);
				}
			}
			
			if(saleReports != null) {
				serviceResponses = generic.messagesReturn(saleReports, PrintVariable.NEGOCIO, messageSource.getMessage("sale.find_ok", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		return serviceResponses;		
	}
	
	private List<Sale> findByHotelIdAndCountryAndDate(String personId, String nationality, String initDate, String endDate, User user) {
		
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
			
		}catch (Exception e) {
			returnSales = null;
		}	
		
		return returnSales;
		
	}
}
