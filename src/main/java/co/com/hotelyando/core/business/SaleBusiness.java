package co.com.hotelyando.core.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.PersonService;
import co.com.hotelyando.core.services.SaleService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.model.User;

@Service
public class SaleBusiness {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PersonService personService;
	
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
				serviceResponse = generic.messageReturn(null, PrintVariable.ADVERTENCIA, messageReturn);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}
	
	public ServiceResponse<Sale> update(Sale sale, User user) {
		
		String messageReturn = "";
		
		try {
			
			sale.setHotelId(user.getHotelId());
			
			messageReturn = saleService.update(sale);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("sale.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.ADVERTENCIA, messageReturn);
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
				serviceResponses = generic.messagesReturn(null, PrintVariable.ADVERTENCIA, messageSource.getMessage("sale.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(sales, PrintVariable.NEGOCIO, messageSource.getMessage("sale.not_content", null, LocaleContextHolder.getLocale()));
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
				serviceResponses = generic.messagesReturn(returnSales, PrintVariable.ADVERTENCIA, messageSource.getMessage("sale.find_ok", null, LocaleContextHolder.getLocale()));
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
				serviceResponse = generic.messageReturn(null, PrintVariable.ADVERTENCIA, messageSource.getMessage("sale.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

}
