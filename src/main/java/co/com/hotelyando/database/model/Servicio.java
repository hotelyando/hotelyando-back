package co.com.hotelyando.database.model;

import lombok.Data;

@Data
public class Servicio {
	
	private Integer servicioId;
	private Integer itemId;
	private String descripcion;
	private Integer cantidad;
	private String valores; // ¿?
	

}
