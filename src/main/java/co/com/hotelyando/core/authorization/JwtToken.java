package co.com.hotelyando.core.authorization;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.database.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * Clase : JWTToken.java
 * Comentario : Clase que genera el token que será utilizado por los demás servicios
 */

public class JwtToken {
	
	public String getJWTToken(User user) {
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(PrintVariable.ROLE_USER);
		Generic<User> genericos = new Generic<>();
		
		String token = Jwts
				.builder()
				.setId(PrintVariable.TENANTJWT)
				.setSubject(genericos.convertirObjetoAJson(user))
				.claim(PrintVariable.AUTHORITIES, grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512, PrintVariable.SECRET.getBytes()).compact();

		return PrintVariable.BEARER + token;
	}
}
