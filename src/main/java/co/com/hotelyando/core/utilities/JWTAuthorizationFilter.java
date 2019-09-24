package co.com.hotelyando.core.utilities;

/*
 * Clase : JWTAuthorizationFilter.java
 * Comentario : Clase que se encarga de validar el token generado por el servicio de login
 */

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException{
		
		try{
		
			if(existeJWTToken(httpServletRequest, httpServletResponse)){
				
				Claims claims = validateToken(httpServletRequest);
				
				if(claims.get(ImpresionVariables.AUTHORITIES) != null){
					setUpSpringAuthentication(claims);
				}else {
					SecurityContextHolder.clearContext();
				}
			}
			
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			
		}catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e){
			
			httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) httpServletResponse).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			
			return;
		}
	}	

	private Claims validateToken(HttpServletRequest httpServletRequest){
		
		Claims claims = null;
		String jwtToken;
		
		jwtToken = httpServletRequest.getHeader(ImpresionVariables.HEADER).replace(ImpresionVariables.PREFIX, "");
		
		claims = Jwts.parser().setSigningKey(ImpresionVariables.SECRET.getBytes()).parseClaimsJws(jwtToken).getBody(); 
		
		return claims;
	}

	/**
	 * Metodo para autenticarnos dentro del flujo de Spring
	 * 
	 * @param claims
	 */
	@SuppressWarnings("unchecked")
	private void setUpSpringAuthentication(Claims claims) {
		
		List<String> authorities;
		UsernamePasswordAuthenticationToken authenticationToken;
		
		authorities = (List<String>) claims.get(ImpresionVariables.AUTHORITIES);
		authenticationToken = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);

	}

	private boolean existeJWTToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		String authenticationHeader = httpServletRequest.getHeader(ImpresionVariables.HEADER);
		
		if (authenticationHeader == null || !authenticationHeader.startsWith(ImpresionVariables.PREFIX)) {
			return false;
		}
			
		return true;
	}

}
