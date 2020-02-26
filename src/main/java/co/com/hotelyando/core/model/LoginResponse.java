package co.com.hotelyando.core.model;

import java.io.Serializable;
import java.util.List;

import co.com.hotelyando.database.model.Hotel;
import lombok.Data;

@Data
public class LoginResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String user;
	private String token;
	private List<Hotel> hotels;

}
