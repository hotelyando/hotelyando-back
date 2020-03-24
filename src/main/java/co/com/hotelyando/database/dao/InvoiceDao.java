package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Invoice;
import co.com.hotelyando.database.repository.IInvoiceRepository;

@Repository
public class InvoiceDao {

	private final IInvoiceRepository iInvoiceRepository;
	
	public InvoiceDao(IInvoiceRepository iInvoiceRepository) {
		this.iInvoiceRepository = iInvoiceRepository;
	}
	
	
	/*
	 * Método que registra una venta en Mongo
	 * @return void
	 */
	public void save(Invoice invoice) throws MongoException, Exception {
		iInvoiceRepository.save(invoice);
	}

	
	/*
	 * Método que actualiza una venta en Mongo
	 * @return void
	 */
	public void update(Invoice invoice) throws MongoException, Exception {
		iInvoiceRepository.save(invoice);
	}
	
	
	/*
	 * Método que retorna todas las ventas realizadas de un hotel
	 * @return List<Invoice>
	 */
	public List<Invoice> findByHotelId(String hotelId) throws MongoException, Exception {
	
		List<Invoice> invoices = iInvoiceRepository.findByHotelId(hotelId);
		
		return invoices;
	}

	
	/*
	 * Método que retorna una venta realizada de un hotel
	 * @return Invoice
	 */
	public Invoice findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Invoice invoice = iInvoiceRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return invoice;
	}
	
	
	/*
	 * Método que retorna las ventas por estado de un hotel
	 * @return List<Invoice>
	 */
	public List<Invoice> findByHotelIdAndState(String hotelId, String state) throws MongoException, Exception {
		
		List<Invoice> invoices = iInvoiceRepository.findByHotelIdAndState(hotelId, state);
		
		return invoices;
		
	}

}
