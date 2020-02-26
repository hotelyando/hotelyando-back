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
	private Client client;
	private Double total;
	private List<Item> items;
	private Room room;
	
	@Data
	class Item {
		private String uuid;
		private String quantity;
		private String values;
		private String total;
	}
	
	@Data 
	class Client {
		private String typeDocument;
		private String document;
		private String name;
	}
}
