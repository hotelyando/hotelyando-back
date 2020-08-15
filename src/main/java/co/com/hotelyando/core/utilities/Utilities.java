package co.com.hotelyando.core.utilities;

import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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
	
	
	public Boolean sendEmailGMail(String sender, String senderPassword, String addressee, String subject, String body) {
		
		Boolean sendEmail = false;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			
		    protected PasswordAuthentication getPasswordAuthentication() {
		    	return new PasswordAuthentication(sender, senderPassword);
		    }
		});
		
		try {
			
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(sender));
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addressee));
		    message.setSubject(subject);
		    message.setText(body);
		    
		    Transport.send(message);
		    
		    sendEmail = true;
		    
		}catch (MessagingException e) {
		}
		
		return sendEmail;	
	}
	
	
	/*
	 * Valida que el password ingresado cumpla con el estadar indicado
	 */
	public Boolean validatePassword(String password) {
		
		RegularExpression regularExpression = new RegularExpression();
		Boolean validatePassword = false;
		
		validatePassword = regularExpression.validateSpecialCharacters(password);
		
		if(validatePassword && password.length() >= 8) {
			validatePassword = true;
		}else {
			validatePassword = false;
		}
		
		return validatePassword;
	}
	
	
}
