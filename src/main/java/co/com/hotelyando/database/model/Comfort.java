package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "comfort")
public class Comfort implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String code;
	private String description;
	private String icon;
	
	
	
	

}
