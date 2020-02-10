package co.com.hotelyando.core.utilities;

import java.util.List;

import com.google.gson.Gson;

import co.com.hotelyando.core.model.RespuestaServicio;
import co.com.hotelyando.core.model.RespuestasServicio;
import co.com.hotelyando.database.model.Usuario;

public class Genericos<T> {

	public String convertirObjetoAJson(T object) {

		Gson gson = new Gson();
		String json = "";

		json = gson.toJson(object);

		return json;
	}

	
	public Usuario convertirJsonAObjeto(String json) {
		
		Gson gson = new Gson();
		Usuario usuario = null;
		
		usuario = gson.fromJson(json, Usuario.class);
		
		return usuario;
	}
	
	/*
	 * Metodo que retorna la respuesta del servicio para un objeto NO lista
	 */
	public RespuestaServicio<T> retornoMensaje(T contenido, String estado, String mensaje){
		
		RespuestaServicio<T> respuestaServicio = new RespuestaServicio<T>();
		respuestaServicio.setContenido(contenido);
		respuestaServicio.setMensaje(mensaje);
		
		return respuestaServicio;
		
	}
	
	/*
	 * Metodo que retorna la respuesta del servicio para un objeto lista
	 */
	public RespuestasServicio<T> retornoMensajes(List<T> contenidos, String estado, String mensaje){
		
		RespuestasServicio<T> respuestasServicio = new RespuestasServicio<T>();
		respuestasServicio.setContenido(contenidos);
		respuestasServicio.setMensaje(mensaje);
		
		return respuestasServicio;
		
	}

}
