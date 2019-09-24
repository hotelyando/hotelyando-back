package co.com.hotelyando.database.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.usuario")
public class Usuario {

	private Integer usuarioId;
	private Integer hotelId;
	private Rol rol;
	private Persona persona;
	private String usuario;
	private String contrasenia;
	private boolean estado;
	
}
