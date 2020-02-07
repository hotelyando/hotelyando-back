package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.item")
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer itemId;
	private Integer hotelId;
	private Boolean activo;
	private Integer cantidad;
	private String descripcion;
	private Integer precio;
	private Integer stock; // en español?
	private List<Tipo> tipo;

}
