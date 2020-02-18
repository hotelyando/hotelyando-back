package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "plan")
public class Plan implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String hotelId;
	private String description;
	private String startDate;
	private String endDate;
	private String registrationDate;
	private String userRegistration;
	private Boolean active; // es la misma variable ESTADO?
	private Boolean test;
	

}
