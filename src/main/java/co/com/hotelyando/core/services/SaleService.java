package co.com.hotelyando.core.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.database.dao.SaleDao;
import co.com.hotelyando.database.model.Sale;

@Service
public class SaleService {

	@Autowired
	private MessageSource messageSource;
	
	private Sale sale;
	
	private final SaleDao saleDao;
	
	public SaleService(SaleDao saleDao) {
		this.saleDao = saleDao;
	}
	
	
	/*
	 * Método que registra una venta de un hotel
	 * @return String
	 */
	public String save(Sale sale) throws MongoException, Exception {
		
		//La factura se crea cuando el cliente confirme su estadia?, si es así, esta se estaria alimentando hasta que el cliente realice el checkout
		String messageReturn = "";
		
		saleDao.save(sale);
			
		return messageReturn;
	}
	
	
	/*
	 * Método que actualiza una venta de un hotel
	 * @return String
	 */
	public String update(Sale sale) throws MongoException, Exception {
		
		//La factura se crea cuando el cliente confirme su estadia?, si es así, esta se estaria alimentando hasta que el cliente realice el checkout
		String messageReturn = "";
		
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
	
	
	public List<Sale> findByHotelIdAndPersonId(String hotelId, String personId) throws MongoException, Exception {
		
		List<Sale> returnSales = new  ArrayList<Sale>();
		List<Sale> sales = saleDao.findByHotelId(hotelId);
		
		for(int a = 0; a < sales.size(); a++) {
			
			if(sales.get(a).getClient().getUuid().equals(personId)) {
				returnSales.add(sales.get(a));
			}
		}
		
		return returnSales;
		
	}

	
	/*
	 * Método que retorna todas las ventas dependiendo de la nacionalidad y el rango de fechas de un hotel
	 * @return List<Sale>
	 */
	public List<Sale> findByHotelIdAndCountryAndDate(String hotelId, String nationality, String initDate, String endDate) throws MongoException, Exception {
		
		String messageReturn = "";
		
		List<Sale> sales = saleDao.findByHotelId(hotelId);
		List<Sale> returnSales = new ArrayList<Sale>();
		
		if(sales != null) {
			
			if(!StringUtils.isBlank(initDate)  &&  !StringUtils.isBlank(endDate)) {
				
				for(int a = 0; a < sales.size(); a++) {
					
					Sale sale = sales.get(a);
					
					LocalDateTime init = LocalDateTime.parse(initDate);
					LocalDateTime end = LocalDateTime.parse(endDate);
					LocalDateTime date = LocalDateTime.parse(sale.getDate());
					
					if(date.isAfter(init) && date.isBefore(end)) {
						returnSales.add(sale);
					}
				}
				
			}else {
				messageReturn = "NO cumple";
			}
			
		}else {
			returnSales = null;
		}
			
		return returnSales;
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
	
	
	/*
	 * Método que retorna una venta por fecha
	 * @return Sale
	 */
	public List<Sale> findByHotelIdAndDateBetween(String hotelId, String from, String to) throws MongoException, Exception {
		
		List<Sale> sales = saleDao.findByHotelIdAndDateBetween(hotelId, from, to);
		
		return sales;
	}
	
	
	/*
	 * Método que retorna las ventas por estado de un hotel
	 * @return List<Sale>
	 */
	public List<Sale> findByHotelIdAndState(String hotelId, String state) throws MongoException, Exception {
		
		List<Sale> sales = saleDao.findByHotelIdAndState(hotelId, state);
		
		return sales;
		
	}

}
