package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.InvoiceService;
import co.com.hotelyando.database.model.Invoice;
import co.com.hotelyando.database.model.User;

@Service
public class InvoiceBusiness {

	private final InvoiceService invoiceService;
	private User objectUser;
	
	public InvoiceBusiness(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
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
