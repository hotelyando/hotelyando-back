package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Service implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String itemId;
	private String description;
	private Integer quantity;
	private String values;
	

}
