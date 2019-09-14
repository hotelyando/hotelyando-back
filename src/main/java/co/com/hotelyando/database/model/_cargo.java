package co.com.hotelyando.database.model;

import lombok.Data;

/**
 * Cargos de los empleados del hotel 
 * @author Santiago
 *
 */
@Data
public class _cargo {
	
	private Integer cargoId;
	private String nombre;
	private String descripcion;
	private Double salario;

}
