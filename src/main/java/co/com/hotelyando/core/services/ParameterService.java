package co.com.hotelyando.core.services;

import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.database.dao.ParameterDao;
import co.com.hotelyando.database.model.Parameter;

@Service
public class ParameterService {
	
	private final ParameterDao parameterDao;
	
	private String messagesReturn;
	
	public ParameterService(ParameterDao parameterDao) {
		this.parameterDao = parameterDao;
	}
	
	
	public String save(Parameter parameter) throws MongoException, Exception {
		
		messagesReturn = "";
		
		parameterDao.save(parameter);
		
		return messagesReturn;
		
	}
	
	
	public String update(Parameter parameter) throws MongoException, Exception {
		
		messagesReturn = "";
		
		parameterDao.update(parameter);
		
		return messagesReturn;
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
