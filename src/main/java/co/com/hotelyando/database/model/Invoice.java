package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "invoice")
public class Invoice implements Serializable{

	private static final long serialVersionUID = 6391454633876244076L;
	
	@Id
	private String uuid;
	private String hotelId;
	private String date;
	private String state;
	private ClientInvoice client;
	private Double total;
	private List<ItemInvoice> items;
	private List<RoomInvoice> rooms;
	
	@Data
	public static class ItemInvoice implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private String uuid;
		private Integer quantity;
		private Double values;
		private Double total;
	}
	
	@Data
	public static class ClientInvoice implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private String typeDocument;
		private String document;
		private String name;
	}
	
	@Data
	static class RoomInvoice implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private String uuid;
		private String description;
		private String roomType;
		private Integer quantity;
		private Double values;
		private Double total;
		private List<ClientInvoice> clientInvoices;
		
	}
}
