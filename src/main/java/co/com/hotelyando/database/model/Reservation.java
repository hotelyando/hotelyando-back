package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "reservation")
public class Reservation implements Serializable{

	private static final long serialVersionUID = 2311499782849326239L;

	@Id
	private String uuid;
	private String hotelId;
	private String reservationDate;
	private String startDate;
	private String exitDate;
	private Integer adultQuantity;
	private Integer childrenQuantity;
	private Boolean fullPayment;
	private Double advancedPayment;
	private Values values;
	private Person person;
	private List<String> roomIds;
	
	
	@Data
	public static class Values {
		
		private Integer gross;
		private Double tax;
		private Double discount;
		private Double total;
	}
}
