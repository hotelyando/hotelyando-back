package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.UsuarioService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.database.model.Usuario;

@Service
public class UsuarioBusiness {

private final UsuarioService usuarioService;
	
	private Genericos<Usuario> genericos;
	private Usuario objetoUsuario;
	
	public UsuarioBusiness(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
		
		genericos = new Genericos<>();
	}
	
	public String registrarUsuario(Usuario usuario, String usuario1) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = usuarioService.registrarUsuario(usuario);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Usuario> consultarUsuariosPorHotel(String usuario) {
		
		List<Usuario> usuarios = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			usuarios = usuarioService.consultarUsuariosPorHotel(objetoUsuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return usuarios;
	}

	public Usuario consultarUsuarioPorHotel(String usuario, Integer usuarioId) {
		
		Usuario usuario1 = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			usuario1 = usuarioService.consultarUsuarioPorHotel(objetoUsuario.getHotelId(), usuarioId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario1;
	}
	
	public Usuario consultarUsuarioYContrasenia(String usuario, String contrasenia) throws Exception {
		
		Usuario usuario1 = null;
		usuario1 = usuarioService.consultarUsuarioYContrasenia(usuario, contrasenia);
		
		return usuario1;
	}


}
