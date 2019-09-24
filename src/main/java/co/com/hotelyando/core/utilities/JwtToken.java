package co.com.hotelyando.core.utilities;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import co.com.hotelyando.database.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * Clase : JWTToken.java
 * Comentario : Clase que genera el token que será utilizado por los demás servicios
 */

public class JwtToken {
	
	public String getJWTToken(Usuario usuario) {
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(ImpresionVariables.ROLE_USER);
		Genericos<Usuario> genericos = new Genericos<>();
		
		String token = Jwts
				.builder()
				.setId(ImpresionVariables.TENANTJWT)
				.setSubject(genericos.convertirObjetoAJson(usuario))
				.claim(ImpresionVariables.AUTHORITIES, grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, ImpresionVariables.SECRET.getBytes()).compact();

		return ImpresionVariables.BEARER + token;
	}
}
