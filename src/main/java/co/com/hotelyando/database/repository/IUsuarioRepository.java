package co.com.hotelyando.database.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.hotelyando.database.model.Usuario;

public interface IUsuarioRepository extends MongoRepository<Usuario, Integer> {
	
	List<Usuario> findByHotelId(Integer hotelId);
	Usuario findByHotelIdAndUsuarioId(Integer hotelId, Integer usuarioId);
	Usuario findByUsuarioAndContrasenia(String usuario, String contrasenia);

}
