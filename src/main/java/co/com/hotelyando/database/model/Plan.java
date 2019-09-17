package co.com.hotelyando.database.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.plan")
public class Plan {

	private Integer planId;
	private Integer hotelId;
	private String descripcion;
	private String fechaInicial;
	private String fechaFinal;
	private String fechaIngreso;
	private Boolean activo; // es la misma variable ESTADO?
	private Boolean prueba;
	

}
