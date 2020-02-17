package co.com.hotelyando.core.utilities;

import java.util.List;

import com.google.gson.Gson;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.database.model.User;

public class Generic<T> {

	public String convertirObjetoAJson(T object) {

		Gson gson = new Gson();
		String json = "";

		json = gson.toJson(object);

		return json;
	}

	
	public User convertirJsonAObjeto(String json) {
		
		Gson gson = new Gson();
		User user = null;
		
		user = gson.fromJson(json, User.class);
		
		return user;
	}
	
	/*
	 * Metodo que retorna la respuesta del servicio para un objeto NO lista
	 */
	public ServiceResponse<T> messageReturn(T content, String state, String message){
		
		ServiceResponse<T> serviceResponse = new ServiceResponse<T>();
		serviceResponse.setContent(content);
		serviceResponse.setMessage(message);
		serviceResponse.setState(state);
		
		return serviceResponse;
		
	}
	
	/*
	 * Metodo que retorna la respuesta del servicio para un objeto lista
	 */
	public ServiceResponses<T> messagesReturn(List<T> contents, String state, String message){
		
		ServiceResponses<T> serviceResponses = new ServiceResponses<T>();
		serviceResponses.setContent(contents);
		serviceResponses.setMessage(message);
		
		return serviceResponses;
		
	}

}
