package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.InvoiceDao;
import co.com.hotelyando.database.model.Invoice;

@Service
public class InvoiceService {

	private final InvoiceDao invoiceDao;
	
	public InvoiceService(InvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}
	
	public String save(Invoice invoice) throws Exception {
		
		String retornoMensaje = "";
		
		//La factura se crea cuando el cliente confirme su estadia?, si es así, esta se estaria alimentando hasta que el cliente realice el checkout
		
		invoiceDao.save(invoice);
			
		return retornoMensaje;
	}
	
	public String update(Invoice invoice) throws Exception {
		
		String retornoMensaje = "";
		
		//La factura se crea cuando el cliente confirme su estadia?, si es así, esta se estaria alimentando hasta que el cliente realice el checkout
		
		invoiceDao.update(invoice);
			
		return retornoMensaje;
	}

	public List<Invoice> findByHotelId(String hotelId) throws Exception {
		
		List<Invoice> invoices = null;
		invoices = invoiceDao.findByHotelId(hotelId);
			
		return invoices;
	}

	public Invoice findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Invoice invoice = null;
		invoice = invoiceDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return invoice;
	}

}
