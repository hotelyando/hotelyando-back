package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String usuarioId;
	private String hotelId;
	private String rol;
	private String personaId;
	private String usuario;
	private String contrasenia;
	private String fechaRegistro;
	private boolean estado;
	
}
