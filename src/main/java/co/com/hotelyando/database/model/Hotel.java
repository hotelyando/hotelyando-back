package co.com.hotelyando.database.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.hotel")
public class Hotel {
	
	private Integer hotelId;
	private String nit;
	private String direccion;
	private String nombre;
	private String altitud;
	private String latitud;
	private List<String> redesSociales;
	private String telefono;
	private Plan plan;
	private List<Sucursal> sucursales;
	

}
