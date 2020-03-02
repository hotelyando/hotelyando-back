package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "room")
public class Room implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String hotelId;
	private String name;
	private String state;
	private String description;
	private String type;

}
