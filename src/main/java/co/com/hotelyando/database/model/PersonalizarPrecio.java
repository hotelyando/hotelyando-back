package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class PersonalizarPrecio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String dia;
	private Double precioDia;
	private Double precioHora;
	private Integer horaInicio;
	private Integer horaFin;
	
}
