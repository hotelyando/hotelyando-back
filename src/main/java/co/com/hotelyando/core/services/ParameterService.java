package co.com.hotelyando.core.services;

import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.database.dao.ParameterDao;
import co.com.hotelyando.database.model.Parameter;

@Service
public class ParameterService {
	
	private final ParameterDao parameterDao;
	
	public ParameterService(ParameterDao parameterDao) {
		this.parameterDao = parameterDao;
	}
	
	
	public String save(Parameter parameter) throws MongoException, Exception {
		
		String messageReturn = "";
		
		parameterDao.save(parameter);
		
		return messageReturn;
		
	}
	
	
	public String update(Parameter parameter) throws MongoException, Exception {
		
		String messageReturn = "";
		
		parameterDao.update(parameter);
		
		return messageReturn;
	}
	
	
	public Parameter findByHotelId(String hotelId) throws MongoException, Exception {
		
		Parameter parameter = parameterDao.findByHotelId(hotelId);
		
		return parameter;
		
	}
	
	
	public Parameter findByUuid(String uuid) throws MongoException, Exception {
		
		Parameter parameter = parameterDao.findByUuid(uuid);
		
		return parameter;
	}

}
