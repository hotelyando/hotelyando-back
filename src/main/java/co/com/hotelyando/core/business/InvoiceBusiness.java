package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.InvoiceService;
import co.com.hotelyando.core.utilities.Generic;
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
	
	public String save(Invoice invoice, User user) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = invoiceService.save(invoice);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Invoice> findByHotelId(User user) {
		
		List<Invoice> invoices = null;
		
		try {
			
			invoices = invoiceService.findByHotelId(objectUser.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return invoices;
	}

	public Invoice findByHotelIdAndUuid(User user, String uuid) {
		
		Invoice invoice = null;
		
		try {
			
			invoice = invoiceService.findByHotelIdAndUuid(objectUser.getHotelId(), uuid);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return invoice;
	}

}
