package co.com.hotelyando.core.utilities;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;

public class Generic<T> {

	public String convertirObjetoAJson(T object) {

		Gson gson = new Gson();
		String json = "";

		json = gson.toJson(object);

		return json;
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
	 * @return ServiceResponses<T>
	 */
	public ServiceResponses<T> messagesReturn(List<T> contents, String state, String message){
		
		ServiceResponses<T> serviceResponses = new ServiceResponses<T>();
		serviceResponses.setContent(contents);
		serviceResponses.setMessage(message);
		serviceResponses.setState(state);
		
		return serviceResponses;
		
	}
	
	
	/*
	 * Método que retorna el mensaje de respuesta HTTP
	 * @return ResponseEntity<ServiceResponse<T>>
	 */
	public ResponseEntity<ServiceResponse<T>> returnResponseController(ServiceResponse<T> serviceResponse){
		
		ResponseEntity<ServiceResponse<T>> responseEntity = null;
		
		if(serviceResponse != null) {
			
			if(serviceResponse.getState().equals(PrintVariables.VALIDACION)) {
				return new ResponseEntity<ServiceResponse<T>>(serviceResponse, HttpStatus.BAD_REQUEST);
			}else if(serviceResponse.getState().equals(PrintVariables.ERROR_BD)) {
				return new ResponseEntity<ServiceResponse<T>>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}else if(serviceResponse.getState().equals(PrintVariables.ERROR_TECNICO)) {
				return new ResponseEntity<ServiceResponse<T>>(serviceResponse, HttpStatus.NOT_FOUND);
			}else if(serviceResponse.getState().equals(PrintVariables.NEGOCIO)) {
				return new ResponseEntity<ServiceResponse<T>>(serviceResponse, HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<ServiceResponse<T>>(serviceResponse, HttpStatus.NO_CONTENT);
		}
		
		return responseEntity;
		
	}
	
	
	/*
	 * Método que retorna el mensaje de respuesta HTTP cuando es una lista
	 * @return ResponseEntity<ServiceResponse<T>>
	 */
	public ResponseEntity<ServiceResponses<T>> returnResponseController(ServiceResponses<T> serviceResponses){
		
		ResponseEntity<ServiceResponses<T>> responseEntity = null;
		
		if(serviceResponses != null) {
		
			if(serviceResponses.getState().equals(PrintVariables.VALIDACION)) {
				return new ResponseEntity<ServiceResponses<T>>(serviceResponses, HttpStatus.BAD_REQUEST);
			}else if(serviceResponses.getState().equals(PrintVariables.ERROR_BD)) {
				return new ResponseEntity<ServiceResponses<T>>(serviceResponses, HttpStatus.INTERNAL_SERVER_ERROR);
			}else if(serviceResponses.getState().equals(PrintVariables.ERROR_TECNICO)) {
				return new ResponseEntity<ServiceResponses<T>>(serviceResponses, HttpStatus.NOT_FOUND);
			}else if(serviceResponses.getState().equals(PrintVariables.NEGOCIO)) {
				return new ResponseEntity<ServiceResponses<T>>(serviceResponses, HttpStatus.OK);
			}
			
		}else {
			return new ResponseEntity<ServiceResponses<T>>(serviceResponses, HttpStatus.NO_CONTENT);
		}
		
		return responseEntity;
		
	}

}
