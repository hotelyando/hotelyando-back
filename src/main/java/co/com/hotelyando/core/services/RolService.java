package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.RolDao;
import co.com.hotelyando.database.model.Rol;

@Service
public class RolService {
	
	private final RolDao rolDao;
	
	public RolService(RolDao rolDao) {
		this.rolDao = rolDao;
	}
	
	public String registrarRol(Rol rol) throws Exception {
		
		String retornoMensaje = "";
		
		retornoMensaje = rolDao.registrarRol(rol);
		
		return retornoMensaje;
	}
	
	public List<Rol> consultarRoles() throws Exception {
		
		List<Rol> roles = null;
		roles = rolDao.consultarRoles();
		
		return roles;
		
	}
	
	public Rol consultarRolPorNombre(String rolNombre) throws Exception {
		
		Rol rol = null;
		
		rol = rolDao.consultarRolPorNombre(rolNombre);
		
		return rol;
		
	}

}
