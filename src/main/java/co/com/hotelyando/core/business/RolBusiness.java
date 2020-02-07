package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.RolService;
import co.com.hotelyando.database.model.Rol;

@Service
public class RolBusiness {
	
	private final RolService rolService;
	
	public RolBusiness(RolService rolService) {
		this.rolService = rolService;
	}
	
	public String registrarRol(Rol rol) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = rolService.registrarRol(rol);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
	}
	
	public List<Rol> consultarRoles() {
		
		List<Rol> roles = null;
		
		try {
			
			roles = rolService.consultarRoles();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roles;
		
	}
}
