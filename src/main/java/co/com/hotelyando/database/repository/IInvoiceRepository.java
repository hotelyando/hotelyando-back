package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Invoice;

public interface IInvoiceRepository extends MongoRepository<Invoice, String> {
	
	List<Invoice> findByHotelId(String hotelId);
	Invoice findByHotelIdAndUuid(String hotelId, String uuid);
	
}
