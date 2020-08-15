package co.com.hotelyando.core.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.database.dao.SaleDao;
import co.com.hotelyando.database.model.Item;
import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.model.Sale.ItemSale;
import co.com.hotelyando.database.model.Sale.RoomSale;
import co.com.hotelyando.database.model.Sale.Values;

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
	 * M�todo que registra una venta de un hotel
	 * @return String
	 */
	public String save(Sale sale) throws MongoException, Exception {
		
		String messageReturn = "";
		
		messageReturn = validateSale(sale);
		
		if(messageReturn.equals("")) {
			saleDao.save(sale);
		}
			
		return messageReturn;
	}
	
	
	/*
	 * M�todo que actualiza una venta de un hotel
	 * @return String
	 */
	public String update(Sale sale) throws MongoException, Exception {
		
		//La factura se crea cuando el cliente confirme su estadia?, si es as�, esta se estaria alimentando hasta que el cliente realice el checkout
		String messageReturn = "";
		
		Double priceItem = 0.0;
		Double priceRoom = 0.0;
		
		if(sale.getItems() != null && sale.getItems().size() > 0) {
			
			for(int a = 0; a < sale.getItems().size(); a++) {
				ItemSale itemSale = sale.getItems().get(a);
				priceItem = priceItem + itemSale.getValues().getTotal();
			}
		}
		
		if(sale.getRooms() != null && sale.getRooms().size() > 0) {
			for(int a = 0; a < sale.getRooms().size(); a++) {
				RoomSale roomSale = sale.getRooms().get(a);
				priceRoom = priceRoom + roomSale.getValues().getTotal();
			}
		}
		
		
		Values values = new Values();
		values.setDiscount(0.0);
		values.setGross(0.0);
		values.setNet(0.0);
		values.setTax(0.19);
		values.setTotal(priceRoom + priceItem); 
		
		sale.setValues(values);
		
		saleDao.update(sale);
			
		return messageReturn;
	}

	
	/*
	 * M�todo que retorna todas las ventas de un hotel
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
	 * M�todo que retorna todas las ventas dependiendo de la nacionalidad y el rango de fechas de un hotel
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
	 * M�todo que retorna una venta de un hotel por Id
	 * @return Sale
	 */
	public Sale findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		Sale sale = saleDao.findByHotelIdAndUuid(hotelId, uuid);
		
		return sale;
	}
	
	
	/*
	 * M�todo que retorna las personas registradas en una venta del hotel
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
	 * M�todo que retorna una venta por fecha
	 * @return Sale
	 */
	public List<Sale> findByHotelIdAndDateBetween(String hotelId, String from, String to) throws MongoException, Exception {
		
		List<Sale> sales = saleDao.findByHotelIdAndDateBetween(hotelId, from, to);
		
		return sales;
	}
	
	
	/*
	 * M�todo que retorna las ventas por estado de un hotel
	 * @return List<Sale>
	 */
	public List<Sale> findByHotelIdAndState(String hotelId, String state) throws MongoException, Exception {
		
		List<Sale> sales = saleDao.findByHotelIdAndState(hotelId, state);
		
		return sales;
		
	}

	
	public String validateSale(Sale sale) {
		
		String messageReturn = "";
		
		
		if(StringUtils.isBlank(sale.getUuid())) {
			messageReturn = messageSource.getMessage("sale.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(sale.getHotelId())) {
			messageReturn = messageSource.getMessage("sale.hotel", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(sale.getDate())) {
			messageReturn = messageSource.getMessage("sale.date", null, LocaleContextHolder.getLocale());
		}else if(sale.getClient() == null) {
			messageReturn = messageSource.getMessage("sale.cliente", null, LocaleContextHolder.getLocale());
		}
		
		return messageReturn;
		
	}
	
	
	public String validaSaleItem(Sale sale) {
		
		String messageReturn = "";
		
		if(sale.getItems() == null || sale.getItems().size() < 1) {
			//messageReturn = messageSource.getMessage("sale.item", null, LocaleContextHolder.getLocale());
			messageReturn = "";
		}else {
			for(int a = 0; a < sale.getItems().size(); a++) {
				
				if(StringUtils.isBlank(sale.getItems().get(a).getUuid())) {
					messageReturn = messageSource.getMessage("sale.item_uuid", null, LocaleContextHolder.getLocale());
				}else if(StringUtils.isBlank(sale.getItems().get(a).getDateSale())) {
					messageReturn = messageSource.getMessage("sale.item_date", null, LocaleContextHolder.getLocale());
				}else if(StringUtils.isBlank(sale.getItems().get(a).getDescription())) {
					messageReturn = messageSource.getMessage("sale.item_description", null, LocaleContextHolder.getLocale());
				}else if(sale.getItems().get(a).getQuantity() < 1) {
					messageReturn = messageSource.getMessage("sale.item_quantity", null, LocaleContextHolder.getLocale());
				}
			}
		}
		
		return messageReturn;
		
	}
	
	
	public String validateRoom(Sale sale) {
		
		String messageReturn = "";
		
		if(sale.getRooms() == null || sale.getRooms().size() < 1) {
			messageReturn = "";
		}else {
			
			for(int a = 0; a < sale.getRooms().size(); a++) {
				
				if(StringUtils.isBlank(sale.getRooms().get(a).getUuid())) {
					messageReturn = messageSource.getMessage("sale.room_uuid", null, LocaleContextHolder.getLocale());
				}else if(StringUtils.isBlank(sale.getRooms().get(a).getDescription())) {
					messageReturn = messageSource.getMessage("sale.room_description", null, LocaleContextHolder.getLocale());
				}else if(StringUtils.isBlank(sale.getRooms().get(a).getStartDate())) {
					messageReturn = messageSource.getMessage("sale.room_startDate", null, LocaleContextHolder.getLocale());
				}else if(StringUtils.isBlank(sale.getRooms().get(a).getEndDate())) {
					messageReturn = messageSource.getMessage("sale.room_endDate", null, LocaleContextHolder.getLocale());
				}else if(sale.getRooms().get(a).getGuests().size() < 1) {
					messageReturn = messageSource.getMessage("sale.room_guest", null, LocaleContextHolder.getLocale());
				}
			}
		}
			
		return messageReturn;
		
	}
}
