package co.com.hotelyando.database.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cargo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cargoId;
	private String nombre;
	private String descripcion;
	private Double salario;

}
