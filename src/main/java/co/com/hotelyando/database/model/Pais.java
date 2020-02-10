package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.pais")
public class Pais implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String paisId;
	private String gentilicio;
	private String nombre;

}
