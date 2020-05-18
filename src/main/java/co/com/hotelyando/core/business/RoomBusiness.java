package co.com.hotelyando.core.business;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.model.ServiceResponse;
import co.com.hotelyando.core.model.ServiceResponses;
import co.com.hotelyando.core.services.RoomService;
import co.com.hotelyando.core.services.RoomTypeService;
import co.com.hotelyando.core.services.SaleService;
import co.com.hotelyando.core.utilities.Generic;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.model.RoomType;
import co.com.hotelyando.database.model.Sale;
import co.com.hotelyando.database.model.User;
import co.com.hotelyando.database.model.Reservation.Values;

@Service
public class RoomBusiness {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
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
	 * M�todo que registra una habitaci�n de hotel
	 * @return ServiceResponse<Room>
	 */
	public ServiceResponse<Room> save(Room room, User user) {
		
		String messageReturn = "";
		
		RoomType roomType = null;
		
		try {
			
			room.setUuid(utilities.generadorId());
			room.setHotelId(user.getHotelId());
			
			//Validamos si el roomType está lleno y si existe, antes de pasar a las validaciones propias de la habitación
			if(room.getRoomTypeUuid() != null) {
				roomType = roomTypeService.findByHotelIdAndRoomType(room.getHotelId(), room.getRoomTypeUuid());
				
				if(roomType == null) {
					messageReturn = messageSource.getMessage("room.type", null, LocaleContextHolder.getLocale());
				}else {
					messageReturn = roomService.save(room);
				}
			}else {
				messageReturn = messageSource.getMessage("room.type", null, LocaleContextHolder.getLocale());
			}
			
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
	 * M�todo que actualiza una habitaci�n de hotel
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
	 * M�todo que retorna la lista de habitaciones de un hotel
	 * @ServiceResponses<Room>
	 */
	public ServiceResponses<Room> findByHotelId(User user) {
		
		try {
			
			List<Room> rooms = roomService.findByHotelId(user.getHotelId());
			
			if(rooms != null) {
				serviceResponses = generic.messagesReturn(rooms, PrintVariable.NEGOCIO, messageSource.getMessage("room.find_ok", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponses = generic.messagesReturn(null, PrintVariable.NOT_CONTENT, messageSource.getMessage("room.not_content", null, LocaleContextHolder.getLocale()));
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
	 * M�todo que retorna una habitaci�n de un hotel
	 * @ServiceResponses<Room>
	 */
	public ServiceResponse<Room> findByHotelIdAndUuid(User user, String uuid) {
		
		try {
			
			Room room = roomService.findByHotelIdAndUuid(user.getHotelId(), uuid);
			
			if(room != null) {
				serviceResponse = generic.messageReturn(room, PrintVariable.NEGOCIO, messageSource.getMessage("room.use_found", null, LocaleContextHolder.getLocale()));
			}else {
				serviceResponse = generic.messageReturn(null, PrintVariable.NOT_CONTENT, messageSource.getMessage("room.use_not_found", null, LocaleContextHolder.getLocale()));
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
	 * M�todo que retorna listado de habitaciones disponibles
	 * @ServiceResponses<Room>
	 */
	public ServiceResponses<Room> findByHotelIdAndBetweenDate(User user, String initDate, String endDate) {
		
		List<Room> rooms = null;
		Room room = null;
		
		
		try {
			
			List<Sale> sales = saleService.findByHotelIdAndState(user.getHotelId(), "Activo");
			
			rooms = new ArrayList<Room>();
			
			if(sales != null) {
					
				for(int a = 0; a < sales.size(); a++) {
					
					Sale sale = sales.get(a);
					
					for(int b = 0; b < sale.getRooms().size(); b++) {
						
						LocalDateTime init = LocalDateTime.parse(initDate);
						LocalDateTime end = LocalDateTime.parse(endDate);
						
						if(init.isBefore(LocalDateTime.parse(sale.getRooms().get(b).getStartDate())) && end.isAfter(LocalDateTime.parse(sale.getRooms().get(b).getEndDate()))) {
						
							room = roomService.findByHotelIdAndUuid(user.getHotelId(), sale.getRooms().get(b).getUuid());
							
							if(room != null) {
								rooms.add(room);
							}
						}
					}
				}
			}
			
			
			List<Room> roomList = roomService.findByHotelId(user.getHotelId());
			
			if(roomList != null) {
				
				for(int c = 0; c < roomList.size(); c++) {
					
					if(roomList.get(c).getState().equals("Disponible") && roomList.get(c).getState().equals("Reservada")) {
						rooms.add(roomList.get(c));
					}
				}
			}
			
			
			if(rooms != null) {
				serviceResponses = generic.messagesReturn(rooms, PrintVariable.NEGOCIO, messageSource.getMessage("room.use_found", null, LocaleContextHolder.getLocale()));
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
	 * Método que elimina una habitación de hotel
	 * @return ServiceResponse<Room>
	 */
	public ServiceResponse<Room> delete(String uuid, User user) {
		
		String messageReturn = "";
		
		try {
			
			messageReturn = roomService.delete(uuid);
			
			if(messageReturn.equals("")) {
				serviceResponse = generic.messageReturn(null, PrintVariable.NEGOCIO, messageSource.getMessage("room.delete_ok", null, LocaleContextHolder.getLocale()));
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
	
}
