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
	private Integer habitacionId;
	private Integer hotelId; // es necesario?
	private Integer paqueteId;
	private Integer uuidDetail; //¿?
	private Integer cantidadPersona; //¿?
	private String valores; //¿?
	private Persona persona; //Huespedes?
	

}
