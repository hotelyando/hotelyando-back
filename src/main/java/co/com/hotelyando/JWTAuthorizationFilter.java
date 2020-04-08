package co.com.hotelyando;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Permit;
import co.com.hotelyando.database.model.User;
import co.com.hotelyando.database.repository.IRoleRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;


/*
 * Clase que se encarga de validar el token antes de consumir algún servicio
 * @author: Hotelyando
 * @version: 01/01/2020
 */
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{
	
	private final transient IRoleRepository iRolRepositoriy;
	private transient boolean permiso;
	private final transient Utilities utilities; 
	
	public JWTAuthorizationFilter(final IRoleRepository iRolRepository) {
		super();
		this.iRolRepositoriy = iRolRepository;
		
		utilities = new Utilities();
	}
	
	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
		
		try {
			
			final Boolean existeToken = utilities.existeJWTToken(request, response);
			
			if (existeToken) {
				
				final User user = utilities.retornoTenant(request, PrintVariable.TOKEN_HEADER);
				
				final Boolean permisoAcceso = validaPermiso(request.getMethod(), request.getRequestURI(), user);
				
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
		
	}	
	
	public boolean validaPermiso(final String metodo, final String url, final User user) {
		
		try {
			
			final List<Permit> permits = iRolRepositoriy.findByUuid(user.getRol()).getPermits();
			
			permits.forEach((value) ->{
				if(value.getMethod().equals(metodo) && value.getName().equals(url.replace("/", ""))) {
					permiso = true;
				}
			});
			
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		return permiso;
	}

}
