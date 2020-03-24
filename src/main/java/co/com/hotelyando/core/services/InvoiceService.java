package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.database.dao.InvoiceDao;
import co.com.hotelyando.database.model.Invoice;

@Service
public class InvoiceService {

	private final InvoiceDao invoiceDao;
	private Invoice invoice;
	
	
	private String messageReturn = "";
	
	public InvoiceService(InvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}
	
	public String save(Invoice invoice) throws MongoException, Exception {
		
		//La factura se crea cuando el cliente confirme su estadia?, si es así, esta se estaria alimentando hasta que el cliente realice el checkout
		messageReturn = "";
		
		invoiceDao.save(invoice);
			
		return messageReturn;
	}
	
	public String update(Invoice invoice) throws MongoException, Exception {
		
		//La factura se crea cuando el cliente confirme su estadia?, si es así, esta se estaria alimentando hasta que el cliente realice el checkout
		messageReturn = "";
		
		invoiceDao.update(invoice);
			
		return messageReturn;
	}

	public List<Invoice> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Invoice> invoices = invoiceDao.findByHotelId(hotelId);
			
		return invoices;
	}

	public Invoice findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Invoice invoice = invoiceDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return invoice;
	}
	
	public Invoice findByDocumentAndDocumentType(String hotelId, String state, String document, String documentType) throws MongoException, Exception {
		
		List<Invoice> invoices = invoiceDao.findByHotelIdAndState(hotelId, state);
		
		invoices.forEach((value) ->{
			if(value.getClient().getDocument().equals(document) && value.getClient().getTypeDocument().equals(documentType)) {
				invoice = value;
			}
		});
		
		return invoice;
		
	}

}
