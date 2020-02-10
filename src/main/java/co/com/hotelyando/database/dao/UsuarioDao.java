package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Usuario;
import co.com.hotelyando.database.repository.IUsuarioRepository;

@Repository
public class UsuarioDao {
	
	private final IUsuarioRepository iUsuarioRepository;
	
	public UsuarioDao (IUsuarioRepository iUsuarioRepository) {
		this.iUsuarioRepository = iUsuarioRepository;
		
	}
	
	public String registrarUsuario(Usuario usuario) throws Exception {
		
		iUsuarioRepository.save(usuario);
		
		return "Ok";
	}

	
	public List<Usuario> consultarUsuariosPorHotel(String hotelId) throws Exception {
		
		List<Usuario> usuarios = null;
		usuarios = iUsuarioRepository.findByHotelId(hotelId);
		
		return usuarios;
	}

	
	public Usuario consultarUsuarioPorHotel(String hotelId, String usuarioId) throws Exception {
		
		Usuario usuario = null;
		usuario = iUsuarioRepository.findByHotelIdAndUsuarioId(hotelId, usuarioId);
		
		return usuario;
	}
	
	public Usuario consultarUsuarioYContrasenia(String usuario, String contrasenia) throws Exception {
		
		Usuario usuario1 = null;
		usuario1 = iUsuarioRepository.findByUsuarioAndContrasenia(usuario, contrasenia);
		
		return usuario1;
	}

}
