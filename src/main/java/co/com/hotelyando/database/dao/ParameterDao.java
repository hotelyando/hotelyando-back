package co.com.hotelyando.database.dao;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;

import co.com.hotelyando.database.model.Parameter;
import co.com.hotelyando.database.repository.IParameterRepository;

@Repository
public class ParameterDao {
	
	private final IParameterRepository iParameterRepository;
	
	public ParameterDao(IParameterRepository iParameterRepository) {
		this.iParameterRepository = iParameterRepository;
	}
	
	
	public void save(Parameter parameter) throws MongoException, Exception {
		
		iParameterRepository.save(parameter);
	}
	
	
	public void update(Parameter parameter) throws MongoException, Exception {
		
		iParameterRepository.save(parameter);
	}
	
	
	public Parameter findByHotelId(String hotelId) throws MongoException, Exception {
		
		Parameter parameter = iParameterRepository.findByHotelId(hotelId);
		
		return parameter;
	}
	
	
	public Parameter findByUuid(String uuid) throws MongoException, Exception {
		
		Parameter parameter = iParameterRepository.findByUuid(uuid);
		
		return parameter;
	}

}
