package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer usuarioId;
	private Integer hotelId;
	private String rol;
	private Persona persona;
	private String usuario;
	private String contrasenia;
	private boolean estado;
	
}
