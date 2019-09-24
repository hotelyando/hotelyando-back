package co.com.hotelyando.database.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.factura")
public class Factura {

	private Integer facturaId;
	private Integer hotelId;
	private String estado;
	private String fechaRegistro;
	private List<Total> totales;
	private Servicio servicio;
	private Habitacion habitacion;
	private Persona persona;
}
