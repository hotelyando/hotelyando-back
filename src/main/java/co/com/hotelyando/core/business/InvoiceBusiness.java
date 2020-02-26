package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.InvoiceService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Invoice;
import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.model.User;

@Service
public class InvoiceBusiness {

	private final InvoiceService invoiceService;
	private User objectUser;
	private ServiceResponse<Invoice> serviceResponse;
	private ServiceResponses<Invoice> serviceResponses;
	private Utilities utilities = null;
	private Generic<Invoice> generic = null;
	
	public InvoiceBusiness(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
		
		serviceResponse = new ServiceResponse<Invoice>();
		serviceResponses = new ServiceResponses<Invoice>();
		utilities = new Utilities();
		generic = new Generic<Invoice>();
		
	}
	
	public ServiceResponse<Invoice> save(Invoice invoice, User user) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = invoiceService.save(invoice);
			
			if(retornoMensaje.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, "Factura registrada!");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, "Error creando la factura!");
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}
	
	public ServiceResponse<Invoice> update(Invoice invoice, User user) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = invoiceService.update(invoice);
			
			if(retornoMensaje.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, "Factura actualizada!");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, "Error actualizando la factura!");
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponse;
	}

	public ServiceResponses<Invoice> findByHotelId(User user) {
		
		List<Invoice> invoices = null;
		
		try {
			
			invoices = invoiceService.findByHotelId(objectUser.getHotelId());
			
			if(invoices == null) {
				serviceResponses = generic.messagesReturn(null, PrintVariables.ADVERTENCIA, "No retornó datos!");
			}else {
				serviceResponses = generic.messagesReturn(invoices, PrintVariables.NEGOCIO, "");
			}
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
			
		return serviceResponses;
	}

	public ServiceResponse<Invoice> findByHotelIdAndUuid(User user, String uuid) {
		
		Invoice invoice = null;
		
		try {
			
			invoice = invoiceService.findByHotelIdAndUuid(objectUser.getHotelId(), uuid);
			
			if(invoice != null) {
				serviceResponse = generic.messageReturn(invoice, PrintVariables.NEGOCIO, "");
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, "No retornó datos!");
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

}
