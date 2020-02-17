package co.com.hotelyando.core.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/*
 * Esta clase retorna la respuesta del servicio, pero cuando el objeto a retornar SI es una lista
 */


@Data
public class ServiceResponses<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<T> content;
	private String state;
	private String message;
	

}
