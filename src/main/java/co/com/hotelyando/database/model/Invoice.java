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
	private String state;
	private String registrationDate;
	private String userRegistration;
	private List<Total> totals;
	private Service service;
	private Room room;
	
}
