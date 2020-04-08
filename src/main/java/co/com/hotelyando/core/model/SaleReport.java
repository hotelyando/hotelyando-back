package co.com.hotelyando.core.model;

import java.io.Serializable;
import java.util.List;

import co.com.hotelyando.database.model.Country;
import lombok.Data;

@Data
public class SaleReport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private String hotelId;
	private String date; //19-11-2020 
	private String state;
	private ClientSale client;
	private Values values;
	private List<ItemSale> items;
	private List<RoomSale> rooms;
	
	@Data
	public static class ItemSale {
		
		private String uuid;
		private String dateSale;
		private String description;
		private Integer quantity;
		private Values values;
	}
	
	@Data
	public static class ClientSale {
		
		  private String uuid;
		  private String documentType;
		  private String document;
		  private String firstName;
		  private String lastName;
		  private String email;
		  private String birthdate;
		  private String phone;
		  private String cellPhone;
		  private String address;
		  private Country country;
		
	}
	
	@Data
	public static class RoomSale {
		
		private String uuid;
		private String description;
		private String startDate;
		private String endDate;
		private Values values;
		private List<ClientSale> guests;
		
	}
	
	@Data
	public static class Values {
		
		private Integer gross;
		private Integer tax;
		private Integer net;
		private Integer discount;
		private Integer total;
	}

}
