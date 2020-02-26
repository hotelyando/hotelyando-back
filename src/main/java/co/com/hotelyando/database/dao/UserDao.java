package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.User;
import co.com.hotelyando.database.repository.IUserRepository;

@Repository
public class UserDao {
	
	private final IUserRepository iUsuarioRepository;
	
	public UserDao (IUserRepository iUsuarioRepository) {
		this.iUsuarioRepository = iUsuarioRepository;
		
	}
	
	public void save(User user) throws Exception {
		iUsuarioRepository.save(user);
	}

	public void update(User user) throws Exception {
		iUsuarioRepository.save(user);
	}
	
	public List<User> findByHotelId(String hotelId) throws Exception {
		
		List<User> users = null;
		users = iUsuarioRepository.findByHotelId(hotelId);
		
		return users;
	}
	
	public User findByHotelIdAndUuid(String hotelId, String uuid) throws Exception {
		
		User user = null;
		user = iUsuarioRepository.findByHotelIdAndUuid(hotelId, uuid);
		
		return user;
	}
	
	public User findByUserAndPassword(String user, String password) throws Exception {
		
		User user1 = null;
		user1 = iUsuarioRepository.findByUserAndPassword(user, password);
		
		return user1;
	}

}
