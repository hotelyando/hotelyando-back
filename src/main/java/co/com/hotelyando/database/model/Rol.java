package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.rol")
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String rolId;
	private String nombre;
	private String descripcion;
	private List<Permiso> permisos;

}
