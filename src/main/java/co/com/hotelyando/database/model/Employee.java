package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "employee")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String hotelId;
	private String personId;
	private String userId;
	private Double salary;
	private String contract;
	private String initDate;
	private String endDate;
	private String position;
	private String dependence;
	private String responsable;
	private String costCenter;
	private String state;
	private String emailEmployee;
	

}
