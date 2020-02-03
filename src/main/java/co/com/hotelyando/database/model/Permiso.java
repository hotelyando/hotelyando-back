package co.com.hotelyando.database.model;

import lombok.Data;

@Data
public class Permiso {
	
	private String permisoId;
	private String nombre;
	private boolean registrar;
	private boolean consultar;
	private boolean borrar;
	private boolean actualizar;
	private String metodo;
	private String path;
	

}
