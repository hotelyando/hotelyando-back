package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.RoomTypeService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.RoomType;
import co.com.hotelyando.database.model.User;

@Service
public class RoomTypeBusiness {

	@Autowired
	private MessageSource messageSource;
	
	private RoomTypeService roomTypeService;
	private ServiceResponse<RoomType> serviceResponse;
	private ServiceResponses<RoomType> serviceResponses;
	private Utilities utilities = null;
	private Generic<RoomType> generic = null;
	
	public RoomTypeBusiness(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
		
		serviceResponse = new ServiceResponse<RoomType>();
		serviceResponses = new ServiceResponses<RoomType>();
		utilities = new Utilities();
		generic = new Generic<RoomType>();
	}
	
	
	/*
	 * Método que se encarga de registrar un tipo de habitación
	 * @return ServiceResponse<RoomType>
	 */
	public ServiceResponse<RoomType> save(RoomType roomType, User user) {
		
		String messageReturn = "";
		
		try {
			
			roomType.setUuid(utilities.generadorId());
			roomType.setHotelId(user.getHotelId());
			
			messageReturn = roomTypeService.save(roomType);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(roomType, PrintVariable.NEGOCIO, messageSource.getMessage("roomtype.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	/*
	 * Método que se encarga de actualizar un tipo de habitación
	 * @return ServiceResponse<RoomType>
	 */
	public ServiceResponse<RoomType> update(RoomType roomType, User user) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = roomTypeService.update(roomType);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(roomType, PrintVariable.NEGOCIO, messageSource.getMessage("roomtype.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.VALIDACION, messageReturn);
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
	
		return serviceResponse;
	}

	
	/*
	 * Método para la búsqueda de un tipo de habitación por uuid
	 * @return ServiceResponse<RoomType>
	 */
	public ServiceResponse<RoomType> findByUuid(User user, String uuid) {
		
		try {
			
			RoomType roomType = roomTypeService.findByHotelIdAndRoomType(user.getHotelId(), uuid);
			
			if(roomType != null) {
				serviceResponse = generic.messageReturn(roomType, PrintVariable.NEGOCIO, messageSource.getMessage("roomtype.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("roomtype.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
		
		return serviceResponse;
	}

	
	/*
	 * Método para la búsqueda de un tipo de habitación por uuid
	 * @return ServiceResponse<RoomType>
	 */
	public ServiceResponses<RoomType> findAll(User user) {
		
		try {
			
			List<RoomType> roomTypes = roomTypeService.findAll(user.getHotelId());
			
			if(roomTypes != null) {
				serviceResponses = generic.messagesReturn(roomTypes, PrintVariable.NEGOCIO, messageSource.getMessage("roomtype.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("roomtype.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}	
		
		return serviceResponses;
	}

	
}
