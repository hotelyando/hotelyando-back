package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Servicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String servicioId;
	private String itemId;
	private String descripcion;
	private Integer cantidad;
	private String valores; // ¿?
	

}
