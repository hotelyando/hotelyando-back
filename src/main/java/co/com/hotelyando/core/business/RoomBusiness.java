package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.RoomService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.model.User;

@Service
public class RoomBusiness {

	@Autowired
	private MessageSource messageSource;
	
	private RoomService roomService;
	private ServiceResponse<Room> serviceResponse;
	private ServiceResponses<Room> serviceResponses;
	private Utilities utilities = null;
	private Generic<Room> generic = null;
	
	public RoomBusiness(RoomService roomService) {
		this.roomService = roomService;
		
		serviceResponse = new ServiceResponse<Room>();
		serviceResponses = new ServiceResponses<Room>();
		utilities = new Utilities();
		generic = new Generic<Room>();
	}
	
	
	/*
	 * Método que registra una habitación de hotel
	 * @return ServiceResponse<Room>
	 */
	public ServiceResponse<Room> save(Room room, User user) {
		
		String messageReturn = "";
		
		try {
			
			room.setUuid(utilities.generadorId());
			room.setHotelId(user.getHotelId());
			
			messageReturn = roomService.save(room);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(room, PrintVariable.NEGOCIO, messageSource.getMessage("room.register_ok", null, LocaleContextHolder.getLocale()));
			}else {
				room.setUuid("");
				serviceResponse = generic.messageReturn(room, PrintVariable.VALIDACION, messageReturn);
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
	 * Método que actualiza una habitación de hotel
	 * @return ServiceResponse<Room>
	 */
	public ServiceResponse<Room> update(Room room, User user) {
		
		String messageReturn = "";
		
		try {
			
			room.setHotelId(user.getHotelId());
			
			messageReturn = roomService.update(room);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(room, PrintVariable.NEGOCIO, messageSource.getMessage("room.update_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(room, PrintVariable.VALIDACION, messageReturn);
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
	 * Método que retorna la lista de habitaciones de un hotel
	 * @ServiceResponses<Room>
	 */
	public ServiceResponses<Room> findByHotelId(User user) {
		
		try {
			
			List<Room> rooms = roomService.findByHotelId(user.getHotelId());
			
			if(rooms != null) {
				serviceResponses = generic.messagesReturn(rooms, PrintVariable.NEGOCIO, messageSource.getMessage("room.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("room.not_content", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
	}


	/*
	 * Método que retorna una habitación de un hotel
	 * @ServiceResponses<Room>
	 */
	public ServiceResponse<Room> findByHotelIdAndUuid(User user, String uuid) {
		
		try {
			
			Room room = roomService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(room != null) {
				serviceResponse = generic.messageReturn(room, PrintVariable.NEGOCIO, messageSource.getMessage("room.use_found", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("room.use_not_found", null, LocaleContextHolder.getLocale()));
			}
			
		}catch (MongoException e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_BD, e.getMessage());
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariable.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

}
