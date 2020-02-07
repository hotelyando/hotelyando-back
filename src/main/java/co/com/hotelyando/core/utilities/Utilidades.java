package co.com.hotelyando.core.utilities;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import co.com.hotelyando.database.model.Usuario;
import io.jsonwebtoken.Jwts;

/*
 * Clase : Utilidades.java
 * Comentario : Clase que contiene metodos transversales para la aplicación
 */

public class Utilidades {
	
	private String tokenHeader;
	private Usuario usuario = null;
	private Gson gson = null;

	/*
	 * Retorna la información del usuario en un objeto Usuario
	 */
	public Usuario retornoTenant(Map<String, String> headers, String nombreEncabezado) {
		
		String token = "";
		
		
		headers.forEach((key,value) ->{
			
			if(key.equals(nombreEncabezado)) {
				tokenHeader = value.toString();
			}
		});
		
		token = Jwts.parser()
                .setSigningKey(ImpresionVariables.SECRET.getBytes())
                .parseClaimsJws(tokenHeader.replace("Bearer", "")) //este metodo es el que valida
                .getBody()
                .getSubject();
		
		gson = new Gson();
		usuario = gson.fromJson(token, Usuario.class);
		
		return usuario;
	}
	
	/*
	 * Valida si el token corresponde a un usuario y siendo verdadero lo retorna un objeto
	 */
	public Usuario retornoTenant(HttpServletRequest request, String nombreEncabezado) {
		
		String token = "";
		String tokenHeader = "";
		
		tokenHeader = request.getHeader(nombreEncabezado).replace(ImpresionVariables.SECRET, "");
		
		if(!tokenHeader.equals("")) {
			
			token = Jwts.parser()
	                .setSigningKey(ImpresionVariables.SECRET.getBytes())
	                .parseClaimsJws(tokenHeader.replace("Bearer", "")) //este metodo es el que valida
	                .getBody()
	                .getSubject();
			
			gson = new Gson();
			usuario = gson.fromJson(token, Usuario.class);
		
		}
		
		return usuario;
		
	}
	
	/*
	 * Valida si existe un token
	 */
	public boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		
		String authenticationHeader = request.getHeader(ImpresionVariables.HEADER);
		
		if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer")) {
			return false;
		}
			
		return true;
	}

}
