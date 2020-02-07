package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.paquete")
public class Paquete implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer paqueteId;
	private Integer hotelId;
	private String descripcion;
	private Integer precio;
	private Integer tiempo; // ¿?
	private List<Item> items; // En español?

}
