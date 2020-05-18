package co.com.hotelyando.core.utilities;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/*
 * Clase : PrintVariable.java
 * Comentario : Clase que se encarga de cargar los mensajes constantes dentro de la aplicaci�n
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
	public static final String NOT_CONTENT = "Contenido no encontrado";
	
	public static final String SAVE = "Save";
	public static final String UPDATE = "Update";
	
	public static final String SALE_CLIENT_ALL = "ALL";
	public static final String SALE_CLIENT_OUTSIDE = "OUTSIDE";
	public static final String SALE_CLIENT_LOCAL = "LOCAL";
	public static final String SALE_LOCAL_COLOMBIA = "COLOMBIA";
	
	
	public static final String INIT_DATE = "2000-01-01T00:00";
	public static final String END_DATE = "2100-01-01T00:00";
	
	
	public static final String EMAIL_SENDER = "hotelyando@gmail.com";
	public static final String EMAIL_SENDER_PASSWORD = "hadmin2019*";
	
	
	public static final String[] STATES_ROOM = {"Disponible", "Reservado", "Ocupado", "Limpieza", "Mantenimiento"};
	
	
	
	
	

}
