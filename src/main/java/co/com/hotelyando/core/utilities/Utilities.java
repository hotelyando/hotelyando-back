package co.com.hotelyando.core.utilities;

import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import co.com.hotelyando.database.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/*
 * Clase : Utilities.java
 * Comentario : Clase que contiene metodos transversales para la aplicaci�n
 */

public class Utilities {
	
	private String tokenHeader;
	private User user = null;
	private Gson gson = null;
	
	/*
	 * Retorna la informaci�n del user en un objeto User
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
	
	
	public void logout(Map<String, String> headers, String nombreEncabezado) {
		
		headers.forEach((key,value) ->{
			
			if(key.equals(nombreEncabezado)) {
				tokenHeader = value.toString();
			}
		});
		
		String jwtToken;
		
		jwtToken = tokenHeader.replace(PrintVariable.PREFIX, "");
		
		Claims claims = Jwts.parser().setSigningKey(PrintVariable.SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
		claims.setExpiration(new Date(System.currentTimeMillis() + 1000));
		
	}
	
	
	public void sendEmailGMail(String addressee, String subject, String body) {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "luisalberto7814@gmail.com";  //Para la dirección nomcuenta@gmail.com

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "Labs2019*");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, addressee);   //Se podrían añadir varios de la misma manera
	        message.setSubject(subject);
	        message.setText(body);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, "Labs2019*");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	} 

}
