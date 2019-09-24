package co.com.hotelyando.core.utilities;

import java.util.Map;

import io.jsonwebtoken.Jwts;

/*
 * Clase : Utilidades.java
 * Comentario : Clase que contiene metodos transversales para la aplicación
 */

public class Utilidades {
	
	private String tokenHeader;
	
	public String retornoTenant(Map<String, String> headers, String nombreEncabezado) {
		
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
		
		return token;
	}
	
}
