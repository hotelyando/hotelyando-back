package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.UsuarioDao;
import co.com.hotelyando.database.model.Usuario;

@Service
public class UsuarioService {

	private final UsuarioDao usuarioDao;
	
	public UsuarioService(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	
	public String registrarUsuario(Usuario usuario) throws Exception {
		
		String retornoMensaje = "";
	
		retornoMensaje = usuarioDao.registrarUsuario(usuario);
		
		return retornoMensaje;
	}

	public List<Usuario> consultarUsuariosPorHotel(Integer hotelId) throws Exception {
		
		List<Usuario> usuarios = null;
		usuarios = usuarioDao.consultarUsuariosPorHotel(hotelId);
		
		return usuarios;
	}

	public Usuario consultarUsuarioPorHotel(Integer hotelId, Integer usuarioId) throws Exception {
		
		Usuario usuario = null;
		usuario = usuarioDao.consultarUsuarioPorHotel(hotelId, usuarioId);
		
		return usuario;
	}

	public Usuario consultarUsuarioYContrasenia(String usuario, String contrasenia) throws Exception {
		
		Usuario usuario1 = null;
		usuario1 = usuarioDao.consultarUsuarioYContrasenia(usuario, contrasenia);
		
		return usuario1;
	}
}
