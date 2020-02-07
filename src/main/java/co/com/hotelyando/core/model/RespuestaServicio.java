package co.com.hotelyando.core.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RespuestaServicio<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private T contenido;
	private String estado;
	private String mensaje;
	

}
