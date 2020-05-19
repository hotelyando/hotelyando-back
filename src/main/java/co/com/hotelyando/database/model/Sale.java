package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data@Document(collection = "sale")
public class Sale implements Serializable{

	private static final long serialVersionUID = 6391454633876244076L;
	
	@Id
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
		private String typeDocument;
		private String document;
		private String name;
		private String country;
		
	}
	
	@Data
	public static class RoomSale {
		
		private String uuid;
		private String description;
		private String startDate;
		private String endDate;
		private Values values;
		private List<String> guests;
		
	}
	
	@Data
	public static class Values {
		
		private Double gross;
		private Double tax;
		private Double net;
		private Double discount;
		private Double total;
	}
}
