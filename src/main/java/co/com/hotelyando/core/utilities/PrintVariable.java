package co.com.hotelyando.core.utilities;

import java.io.Serializable;

import lombok.Data;

/*
 * Clase : PrintVariable.java
 * Comentario : Clase que se encarga de cargar los mensajes constantes dentro de la aplicación
 */
@Data
public class PrintVariable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String HEADER = "Authorization";
	public static final String TOKEN_HEADER = "authorization";
	public static final String PREFIX = "Bearer ";
	public static final String SECRET = "mySecretKey";
	public static final String AUTHORITIES = "authorities";
	public static final String TENANTJWT = "tenantkJWT";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String BEARER = "Bearer ";
	
	public static final String ERROR_BD = "Error base de datos";
	public static final String ERROR_TECNICO = "Error técnico";
	public static final String NEGOCIO = "Negocio";
	public static final String ADVERTENCIA = "Advertencia";
	public static final String VALIDACION = "Validación de datos";
	
	public static final String SAVE = "Save";
	public static final String UPDATE = "Update";
	
	
	
	
	
	
	

}
