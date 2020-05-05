package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.repository.ISaleRepository;

@Repository
public class SaleDao {

	private final ISaleRepository iSaleRepository;
	
	public SaleDao(ISaleRepository iSaleRepository) {
		this.iSaleRepository = iSaleRepository;
	}
	
	
	/*
	 * Método que registra una venta en Mongo
	 * @return void
	 */
	public void save(Sale sale) throws MongoException, Exception {
		iSaleRepository.save(sale);
	}

	
	/*
	 * Método que actualiza una venta en Mongo
	 * @return void
	 */
	public void update(Sale sale) throws MongoException, Exception {
		iSaleRepository.save(sale);
	}
	
	
	/*
	 * Método que retorna todas las ventas realizadas de un hotel
	 * @return List<Sale>
	 */
	public List<Sale> findByHotelId(String hotelId) throws MongoException, Exception {
	
		List<Sale> sales = iSaleRepository.findByHotelId(hotelId);
		
		return sales;
	}

	
	/*
	 * Método que retorna una venta realizada de un hotel
	 * @return Sale
	 */
	public Sale findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Sale sale = iSaleRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return sale;
	}
	
	
	/*
	 * Método que retorna las ventas por estado de un hotel
	 * @return List<Sale>
	 */
	public List<Sale> findByHotelIdAndState(String hotelId, String state) throws MongoException, Exception {
		
		List<Sale> sales = iSaleRepository.findByHotelIdAndState(hotelId, state);
		
		return sales;
		
	}
	
	
	/*
	 * Método que retorna una venta por fecha
	 * @return Sale
	 */
	public List<Sale> findByHotelIdAndDateBetween(String hotelId, String from, String to) throws MongoException, Exception {
		
		List<Sale> sales = iSaleRepository.findByHotelIdAndDateBetween(hotelId, from, to);
		
		return sales;
	}

}
