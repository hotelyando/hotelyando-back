package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.habitacion")
public class Habitacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String habitacionId;
	private String hotelId; // es necesario?
	private String paqueteId;
	private String uuidDetail; //¿?
	private Integer cantidadPersona; //¿?
	private String valores; //¿?
	private Persona persona; //Huespedes?
	

}
