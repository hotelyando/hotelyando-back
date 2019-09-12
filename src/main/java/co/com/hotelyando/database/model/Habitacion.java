package co.com.hotelyando.database.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.habitacion")
public class Habitacion {
	
	private Integer habitacionId;
	private Integer hotelId; // es necesario?
	private Integer paqueteId;
	private Integer uuidDetail; //¿?
	private Integer cantidadPersona; //¿?
	private String valores; //¿?
	private Persona persona; //Huespedes?
	

}
