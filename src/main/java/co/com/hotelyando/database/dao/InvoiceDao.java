package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Invoice;
import co.com.hotelyando.database.repository.IInvoiceRepository;

@Repository
public class InvoiceDao {

	private final IInvoiceRepository iInvoiceRepository;
	
	public InvoiceDao(IInvoiceRepository iInvoiceRepository) {
		this.iInvoiceRepository = iInvoiceRepository;
	}
	
	public void save(Invoice invoice) throws Exception {
		iInvoiceRepository.save(invoice);
	}

	public void update(Invoice invoice) throws Exception {
		iInvoiceRepository.save(invoice);
	}
	
	
	public List<Invoice> findByHotelId(String hotelId) throws Exception {
	
		List<Invoice> invoices = null;
		invoices = iInvoiceRepository.findByHotelId(hotelId);
		
		return invoices;
	}

	
	public Invoice findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		Invoice invoice = null;
		invoice = iInvoiceRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return invoice;
	}

}
