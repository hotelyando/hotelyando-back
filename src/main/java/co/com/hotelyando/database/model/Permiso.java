package co.com.hotelyando.database.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Permiso implements Serializable{

	private static final long serialVersionUID = 1L;
	private String permisoId;
	private String nombre;
	private boolean registrar;
	private boolean consultar;
	private boolean borrar;
	private boolean actualizar;
	private String metodo;
	private String path;
	

}
