package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.ImpresionEntidades;
import co.com.hotelyando.core.utilities.ImpresionVariables;
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
	
		if(StringUtils.isBlank(usuario.getUsuarioId().toString())) {
			retornoMensaje = ImpresionEntidades.VALIDACION_ID_USUARIO;
		}else if(StringUtils.isBlank(usuario.getHotelId().toString())) {
			retornoMensaje = ImpresionEntidades.VALIDACION_HOTEL_ID;
		}else if(StringUtils.isBlank(usuario.getRol())) {
			retornoMensaje = ImpresionEntidades.VALIDACION_ROL;
		}else if(StringUtils.isBlank(usuario.getPersonaId())) {
			retornoMensaje = ImpresionEntidades.VALIDACION_PERSONA;
		}else if(StringUtils.isBlank(usuario.getUsuario())) {
			retornoMensaje = ImpresionEntidades.VALIDACION_USUARIO;
		}else if(StringUtils.isBlank(usuario.getContrasenia())) {
			retornoMensaje = ImpresionEntidades.VALIDACION_CONTRASENA;
		}else {
			usuarioDao.registrarUsuario(usuario);
		}
		
		return retornoMensaje;
	}

	
	public List<Usuario> consultarUsuariosPorHotel(String hotelId) throws Exception {
		
		List<Usuario> usuarios = null;
		usuarios = usuarioDao.consultarUsuariosPorHotel(hotelId);
		
		return usuarios;
	}

	
	public Usuario consultarUsuarioPorHotel(String hotelId, String usuarioId) throws Exception {
		
		Usuario usuario = null;
		usuario = usuarioDao.consultarUsuarioPorHotel(hotelId, usuarioId);
		
		return usuario;
	}

	
	public Usuario consultarUsuarioYContrasenia(String usuarioLogin, String contrasenia) throws Exception {
		
		Usuario usuario = new Usuario();
		
		if(StringUtils.isBlank(usuarioLogin)) {
			usuario.setUsuarioId(ImpresionVariables.VALIDACION);
		}else if(StringUtils.isBlank(contrasenia)) {
			usuario.setUsuarioId(ImpresionVariables.VALIDACION);
		}else {
			usuario = usuarioDao.consultarUsuarioYContrasenia(usuarioLogin, contrasenia);
		}
		
		return usuario;
	}
}
