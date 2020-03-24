package co.com.hotelyando.database.repository;

import java.util.List;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Invoice;

@JaversSpringDataAuditable
public interface IInvoiceRepository extends MongoRepository<Invoice, String> {
	
	List<Invoice> findByHotelId(String hotelId);
	Invoice findByHotelIdAndUuid(String hotelId, String uuid);
	List<Invoice> findByHotelIdAndState(String hotelId, String state);
	
}
