package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.User;
import co.com.hotelyando.database.repository.IUserRepository;

@Repository
public class UserDao {
	
	private final IUserRepository iUsuarioRepository;
	
	public UserDao (IUserRepository iUsuarioRepository) {
		this.iUsuarioRepository = iUsuarioRepository;
		
	}
	
	public void save(User user) throws MongoException, Exception {
		iUsuarioRepository.save(user);
	}

	public void update(User user) throws MongoException, Exception {
		iUsuarioRepository.save(user);
	}
	
	public List<User> findByHotelId(String hotelId) throws MongoException, Exception {
		
		List<User> users = iUsuarioRepository.findByHotelId(hotelId);
		
		return users;
	}
	
	public User findByHotelIdAndUuid(String hotelId, String uuid) throws MongoException, Exception {
		
		User user = iUsuarioRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return user;
	}
	
	public User findByUserAndPassword(String login, String password) throws MongoException, Exception {
		
		User user = iUsuarioRepository.findByUserAndPassword(login, password);
		
		return user;
	}
	
	public User findByUser(String hotelId, String login) throws MongoException, Exception {
		
		User user = iUsuarioRepository.findByHotelIdAndUser(hotelId, login);
		
		return user;
	}
	
	
	public User findByHotelIdAndPersonId(String hotelId, String personId) throws MongoException, Exception {
		
		User user = iUsuarioRepository.findByHotelIdAndPersonId(hotelId, personId);
		
		return user;
		
	}

}
