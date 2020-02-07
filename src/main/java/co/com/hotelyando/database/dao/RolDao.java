package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Rol;
import co.com.hotelyando.database.repository.IRolRepository;

@Repository
public class RolDao {
	
	private final IRolRepository iRolRepository;
	
	public RolDao(IRolRepository iRolRepository) {
		this.iRolRepository = iRolRepository;
	}

	public String registrarRol(Rol rol) throws Exception {
		
		iRolRepository.save(rol);
		
		return "Ok";
	}
	
	public List<Rol> consultarRoles() throws Exception {
		
		List<Rol> roles = null;
		roles = iRolRepository.findAll();
		
		return roles;
		
	}
	
	public Rol consultarRolPorNombre(String rolNombre) throws Exception {
		
		Rol rol = null;
		
		rol = iRolRepository.findByNombre(rolNombre);
		
		return rol;
		
	}
	
}
