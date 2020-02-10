package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.plan")
public class Plan implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String planId;
	private String hotelId;
	private String descripcion;
	private String fechaInicial;
	private String fechaFinal;
	private String fechaIngreso;
	private Boolean activo; // es la misma variable ESTADO?
	private Boolean prueba;
	

}
