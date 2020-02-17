package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user")
public class User implements Serializable{

	private static final long serialVersionUID = -1871508607965273613L;
	
	@Id
	private String uuid;
	private String hotelId;
	private String rol;
	private String personId;
	private String user;
	private String password;
	private String registrationDate;
	private boolean state;
	
}
