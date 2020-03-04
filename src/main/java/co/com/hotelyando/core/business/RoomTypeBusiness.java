package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.RoomTypeService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.core.utilities.PrintVariables;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.RoomType;
import co.com.hotelyando.database.model.User;

@Service
public class RoomTypeBusiness {

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
	
	public ServiceResponse<RoomType> save(RoomType roomType, User user) {
		
		String messageReturn = "";
		
		try {
			
			roomType.setUuid(utilities.generadorId());
			
			messageReturn = roomTypeService.save(roomType);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, PrintEntity.TIPO_HABITACION_REGISTRADA);
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, messageReturn);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	
	public ServiceResponse<RoomType> update(RoomType roomType, User user) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = roomTypeService.update(roomType);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariables.NEGOCIO, PrintEntity.TIPO_HABITACION_REGISTRADA);
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariables.ADVERTENCIA, messageReturn);
			}
			
		}catch (Exception e) {
			serviceResponse = generic.messageReturn(null, PrintVariables.ERROR_TECNICO, e.getMessage());
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
}
