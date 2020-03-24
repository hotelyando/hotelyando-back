package co.com.hotelyando.core.utilities;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import co.com.hotelyando.database.model.User;
import io.jsonwebtoken.Jwts;

/*
 * Clase : Utilities.java
 * Comentario : Clase que contiene metodos transversales para la aplicación
 */

public class Utilities {
	
	private String tokenHeader;
	private User user = null;
	private Gson gson = null;
	
	/*
	 * Retorna la información del user en un objeto User
	 */
	public User returnTenant(Map<String, String> headers, String nombreEncabezado) {
		
		String token;
		
		headers.forEach((key,value) ->{
			
			if(key.equals(nombreEncabezado)) {
				tokenHeader = value.toString();
			}
		});
		
		token = Jwts.parser()
                .setSigningKey(PrintVariable.SECRET.getBytes())
                .parseClaimsJws(tokenHeader.replace("Bearer", "")) //este metodo es el que valida
                .getBody()
                .getSubject();
		
		gson = new Gson();
		user = gson.fromJson(token, User.class);
		
		return user;
	}
	
	/*
	 * Valida si el token corresponde a un user y siendo verdadero lo retorna un objeto
	 */
	public User retornoTenant(HttpServletRequest request, String nombreEncabezado) {
		
		String token;
		String tokenHeader;
		
		tokenHeader = request.getHeader(nombreEncabezado).replace(PrintVariable.SECRET, "");
		
		if(!tokenHeader.equals("")) {
			
			token = Jwts.parser()
	                .setSigningKey(PrintVariable.SECRET.getBytes())
	                .parseClaimsJws(tokenHeader.replace("Bearer", "")) //este metodo es el que valida
	                .getBody()
	                .getSubject();
			
			gson = new Gson();
			user = gson.fromJson(token, User.class);
		
		}
		
		return user;
		
	}
	
	/*
	 * Valida si existe un token
	 */
	public boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		
		String authenticationHeader = request.getHeader(PrintVariable.HEADER);
		
		if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer")) {
			return false;
		}
			
		return true;
	}
	
	
	/*
	 * Generador de ID de object
	 */
	public String generadorId() {
		
		String retornoId = String.valueOf(System.currentTimeMillis()) + String.valueOf(UUID.randomUUID()).replace("-", "");
		
		return retornoId;
		
	}

}
