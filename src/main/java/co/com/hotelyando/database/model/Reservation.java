package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "reserva")
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
	private Person person;
}
