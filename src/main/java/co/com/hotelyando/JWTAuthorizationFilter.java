package co.com.hotelyando;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.com.hotelyando.core.utilities.ImpresionVariables;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Usuario;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		
		Utilidades utilidades = null;
		Usuario usuario = null;
		Boolean existeToken = false;
		Boolean permisoAcceso = false;
		
		try {
			
			utilidades = new Utilidades();
			
			existeToken = utilidades.existeJWTToken(request, response);
			
			if (existeToken) {
				
				usuario = utilidades.retornoTenant(request, ImpresionVariables.TOKEN_HEADER);
				
				permisoAcceso = utilidades.validaPermiso(request.getMethod(), request.getRequestURI(), usuario);
				
				if(!permisoAcceso) {
					SecurityContextHolder.clearContext();
					throw new JwtException("UnAuthorized");
				}
			}
			
			chain.doFilter(request, response);
			
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			
		}catch (JwtException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}
		
		return;
	}	

}
