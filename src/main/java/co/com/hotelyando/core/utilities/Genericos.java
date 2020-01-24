package co.com.hotelyando.core.utilities;

import java.util.List;

import com.google.gson.Gson;

import co.com.hotelyando.core.model.LoginResponse;
import co.com.hotelyando.core.model.Mensaje;
import co.com.hotelyando.core.model.RespuestaServicio;
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
	
	public RespuestaServicio<T> retornoMensaje(T contenido, String estado, List<String> mensajes){
		
		RespuestaServicio<T> respuestaServicio = new RespuestaServicio<T>();
		Mensaje mensaje = new Mensaje();
		
		mensaje.setEstado(estado);
		mensaje.setMensajes(mensajes);
		
		respuestaServicio.setContenido(contenido);
		respuestaServicio.setMensajes(mensaje);
		
		return respuestaServicio;
		
	}
	
	
	/*@SuppressWarnings("unchecked")
	public T convertirJsonAObjeto(String json) {
		
		Gson gson = new Gson();
		T object = null;
		
		object = (T) gson.fromJson(json, Object.class);
		
		return object;
		
		
	}*/

}
