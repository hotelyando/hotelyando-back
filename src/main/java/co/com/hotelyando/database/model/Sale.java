package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "invoice")
public class Sale implements Serializable{

	private static final long serialVersionUID = 6391454633876244076L;
	
	@Id
	private String uuid;
	private String hotelId;
	private String date;
	private String state;
	private ClientSale client;
	private Double total;
	private List<ItemSale> items;
	private List<RoomSale> rooms;
	
	@Data
	public static class ItemSale {
		
		private String uuid;
		private String dateSale;
		private Integer quantity;
		private Double values;
		private Double total;
	}
	
	@Data
	public static class ClientSale {
		
		private String typeDocument;
		private String document;
		private String name;
	}
	
	@Data
	static class RoomSale {
		
		private String uuid;
		private String description;
		private String startDate;
		private String endDate;
		private String roomType;
		private Value value;
		private List<ClientSale> clientInvoices;
		
	}
	
	@Data
	static class Value {
		
		private Integer gross;
		private Integer tax;
		private Integer net;
		private Integer discount;
		private Integer total;
	}
}
