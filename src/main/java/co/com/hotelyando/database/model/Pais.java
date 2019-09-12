package co.com.hotelyando.database.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.pais")
public class Pais { //Duda
	
	private Integer paisId;
	private String gentilicio;
	private String nombre;

}
