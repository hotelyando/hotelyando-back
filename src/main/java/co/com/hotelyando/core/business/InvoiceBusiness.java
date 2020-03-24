package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.InvoiceService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Invoice;
import co.com.hotelyando.database.model.User;

@Service
public class InvoiceBusiness {

	private final InvoiceService invoiceService;
	private ServiceResponse<Invoice> serviceResponse;
	private ServiceResponses<Invoice> serviceResponses;
	private Generic<Invoice> generic = null;
	private Utilities utilities;
	
	private String messageReturn;
	
	public InvoiceBusiness(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
		
		serviceResponse = new ServiceResponse<Invoice>();
		serviceResponses = new ServiceResponses<Invoice>();
		generic = new Generic<Invoice>();
		utilities = new Utilities();
		
	}
	
	public ServiceResponse<Invoice> save(Invoice invoice, User user) {
		
		try {
			
			invoice.setHotelId(user.getHotelId());
			invoice.setUuid(utilities.generadorId());
			
			messageReturn = invoiceService.save(invoice);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(invoice, PrintVariable.NEGOCIO, "Factura registrada!");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.ADVERTENCIA, "Error creando la factura!");
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}
	
	public ServiceResponse<Invoice> update(Invoice invoice, User user) {
		
		try {
			
			invoice.setHotelId(user.getHotelId());
			
			messageReturn = invoiceService.update(invoice);
			
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

	public ServiceResponses<Invoice> findByHotelId(User user) {
		
		try {
			
			List<Invoice> invoices = invoiceService.findByHotelId(user.getHotelId());
			
			if(invoices == null) {
				serviceResponses = generic.messagesReturn(null, PrintVariable.ADVERTENCIA, "No retornó datos!");
			}else {
				serviceResponses = generic.messagesReturn(invoices, PrintVariable.NEGOCIO, "");
			}
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}

	public ServiceResponse<Invoice> findByHotelIdAndUuid(User user, String uuid) {
		
		try {
			
			Invoice invoice = invoiceService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(invoice != null) {
				serviceResponse = generic.messageReturn(invoice, PrintVariable.NEGOCIO, "");
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
