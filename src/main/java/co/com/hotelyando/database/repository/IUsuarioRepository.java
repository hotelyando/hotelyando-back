package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Usuario;

public interface IUsuarioRepository extends MongoRepository<Usuario, Integer> {
	
	List<Usuario> findByHotelId(String hotelId);
	Usuario findByHotelIdAndUsuarioId(String hotelId, String usuarioId);
	Usuario findByUsuarioAndContrasenia(String usuario, String contrasenia);

}
