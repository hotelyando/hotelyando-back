package co.com.hotelyando;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Permit;
import co.com.hotelyando.database.model.User;
import co.com.hotelyando.database.repository.IRoleRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{
	
	private IRoleRepository iRolRepositoriy;
	private boolean permiso = false;
	
	public JWTAuthorizationFilter(IRoleRepository iRolRepository) {
		this.iRolRepositoriy = iRolRepository;
	
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		
		Utilities utilities = null;
		User user = null;
		Boolean existeToken = false;
		Boolean permisoAcceso = false;
		
		try {
			
			utilities = new Utilities();
			
			existeToken = utilities.existeJWTToken(request, response);
			
			if (existeToken) {
				
				user = utilities.retornoTenant(request, PrintVariables.TOKEN_HEADER);
				
				permisoAcceso = validaPermiso(request.getMethod(), request.getRequestURI(), user);
				
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
	
	public boolean validaPermiso(String metodo, String url, User user) {
		
		List<Permit> permits = new ArrayList<Permit>();
		
		try {
			
			permits = iRolRepositoriy.findByName(user.getRol()).getPermits();
			
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
