package co.com.hotelyando.core.model;

import java.io.Serializable;

public class RespuestaServicio<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private T contenido;
	private Mensaje mensajes;
	
	public T getContenido() {
		return contenido;
	}
	public void setContenido(T contenido) {
		this.contenido = contenido;
	}
	public Mensaje getMensajes() {
		return mensajes;
	}
	public void setMensajes(Mensaje mensajes) {
		this.mensajes = mensajes;
	}
	
	

}
