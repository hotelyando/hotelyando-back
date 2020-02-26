package co.com.hotelyando.core.utilities;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import co.com.hotelyando.database.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * Clase : JWTToken.java
 * Comentario : Clase que genera el token que será utilizado por los demás servicios
 */

public class JwtToken {
	
	public String getJWTToken(User user) {
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(PrintVariables.ROLE_USER);
		Generic<User> genericos = new Generic<>();
		
		String token = Jwts
				.builder()
				.setId(PrintVariables.TENANTJWT)
				.setSubject(genericos.convertirObjetoAJson(user))
				.claim(PrintVariables.AUTHORITIES, grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, PrintVariables.SECRET.getBytes()).compact();

		return PrintVariables.BEARER + token;
	}
}
