package co.com.hotelyando.database.model;

import lombok.Data;

/**
 * Roles de los usuarios del hotel
 * @author Santiago
 *
 */
@Data
public class _rol {
	
	private Integer rolId;
	private String nombre;
	private String descripcion;

}
