package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.factura")
public class Factura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer facturaId;
	private Integer hotelId;
	private String estado;
	private String fechaRegistro;
	private List<Total> totales;
	private Servicio servicio;
	private Habitacion habitacion;
	private Persona persona;
}
