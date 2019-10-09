package co.com.hotelyando.core.model;

import co.com.hotelyando.database.model.Usuario;

public class ResponseService {
	
	private Usuario usuario;
	private Status status;
	private String response;
	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}