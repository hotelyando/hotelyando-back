package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.database.dao.SaleDao;
import co.com.hotelyando.database.model.Sale;

@Service
public class SaleService {

	private final SaleDao saleDao;
	private Sale sale;
	
	
	private String messageReturn = "";
	
	public SaleService(SaleDao saleDao) {
		this.saleDao = saleDao;
	}
	
	
	/*
	 * Método que registra una venta de un hotel
	 * @return String
	 */
	public String save(Sale sale) throws MongoException, Exception {
		
		//La factura se crea cuando el cliente confirme su estadia?, si es así, esta se estaria alimentando hasta que el cliente realice el checkout
		messageReturn = "";
		
		saleDao.save(sale);
			
		return messageReturn;
	}
	
	
	/*
	 * Método que actualiza una venta de un hotel
	 * @return String
	 */
	public String update(Sale sale) throws MongoException, Exception {
		
		//La factura se crea cuando el cliente confirme su estadia?, si es así, esta se estaria alimentando hasta que el cliente realice el checkout
		messageReturn = "";
		
		saleDao.update(sale);
			
		return messageReturn;
	}

	
	/*
	 * Método que retorna todas las ventas de un hotel
	 * @return List<Sale>
	 */
	public List<Sale> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<Sale> sales = saleDao.findByHotelId(hotelId);
			
		return sales;
	}

	
	/*
	 * Método que retorna una venta de un hotel por Id
	 * @return Sale
	 */
	public Sale findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Sale sale = saleDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return sale;
	}
	
	
	/*
	 * Método que retorna las personas registradas en una venta del hotel
	 * @return Sale
	 */
	public Sale findByDocumentAndDocumentType(String hotelId, String state, String document, String documentType) throws MongoException, Exception {
		
		List<Sale> sales = saleDao.findByHotelIdAndState(hotelId, state);
		
		sales.forEach((value) ->{
			if(value.getClient().getDocument().equals(document) && value.getClient().getTypeDocument().equals(documentType)) {
				sale = value;
			}
		});
		
		return sale;
		
	}

}
