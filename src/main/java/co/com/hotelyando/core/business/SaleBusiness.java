package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.SaleService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.model.User;

@Service
public class SaleBusiness {

	private final SaleService saleService;
	private ServiceResponse<Sale> serviceResponse;
	private ServiceResponses<Sale> serviceResponses;
	private Generic<Sale> generic = null;
	private Utilities utilities;
	
	private String messageReturn;
	
	public SaleBusiness(SaleService saleService) {
		this.saleService = saleService;
		
		serviceResponse = new ServiceResponse<Sale>();
		serviceResponses = new ServiceResponses<Sale>();
		generic = new Generic<Sale>();
		utilities = new Utilities();
		
	}
	
	public ServiceResponse<Sale> save(Sale sale, User user) {
		
		try {
			
			sale.setHotelId(user.getHotelId());
			sale.setUuid(utilities.generadorId());
			
			messageReturn = saleService.save(sale);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(sale, PrintVariable.NEGOCIO, "Factura registrada!");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.ADVERTENCIA, "Error creando la factura!");
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}
	
	public ServiceResponse<Sale> update(Sale sale, User user) {
		
		try {
			
			sale.setHotelId(user.getHotelId());
			
			messageReturn = saleService.update(sale);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, "Factura actualizada!");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.ADVERTENCIA, "Error actualizando la factura!");
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
				serviceResponses = generic.messagesReturn(null, PrintVariable.ADVERTENCIA, "No retornó datos!");
			}else {
				serviceResponses = generic.messagesReturn(sales, PrintVariable.NEGOCIO, "");
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
				serviceResponse = generic.messageReturn(null, PrintVariable.ADVERTENCIA, "No retornó datos!");
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

}
