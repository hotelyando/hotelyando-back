package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.RoomService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.model.User;

@Service
public class RoomBusiness {

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
	
	public ServiceResponse<Room> save(Room room, User user) {
		
		String messageReturn = "";
		
		try {
			
			room.setUuid(utilities.generadorId());
			room.setHotelId(user.getHotelId());
			
			messageReturn = roomService.save(room);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, PrintEntity.HABITACION_REGISTRADA);
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, messageReturn);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	public ServiceResponse<Room> update(Room room, User user) {
		
		String messageReturn = "";
		
		try {
			
			room.setHotelId(user.getHotelId());
			
			messageReturn = roomService.update(room);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, PrintEntity.HABITACION_REGISTRADA);
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, messageReturn);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

	/*
	 * 
	 */
	public ServiceResponses<Room> findByHotelId(User user) {
		
		List<Room> rooms = null;
		
		try {
			
			rooms = roomService.findByHotelId(user.getHotelId());
			
			serviceResponses = generic.messagesReturn(rooms, PrintVariables.NEGOCIO, "");
			
		}catch (Exception e) {
			serviceResponses = generic.messagesReturn(rooms, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponses;
	}

	/*
	 * 
	 */
	public ServiceResponse<Room> findByHotelIdAndUuid(User user, String uuid) {
		
		Room room = null;
		
		try {
			
			room = roomService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			serviceResponse = generic.messageReturn(room, PrintVariables.NEGOCIO, "");
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(room, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

}
