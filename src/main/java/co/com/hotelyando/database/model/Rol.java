package co.com.hotelyando.database.model;

import java.util.List;

import lombok.Data;

@Data
public class Rol {
	
	private Integer rolId;
	private String nombre;
	private String descripcion;
	private List<Permiso> permisos;

}
