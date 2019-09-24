package co.com.hotelyando.database.model;

import lombok.Data;

@Data
public class Cargo {
	
	private Integer cargoId;
	private String nombre;
	private String descripcion;
	private Double salario;

}
