package co.com.hotelyando.core.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.database.dao.RoomTypeDao;
import co.com.hotelyando.database.model.RoomType;

@Service
public class RoomTypeService {

	private RoomTypeDao tipoRoomTypeDao;
	
	public RoomTypeService(RoomTypeDao tipoRoomTypeDao) {
		this.tipoRoomTypeDao = tipoRoomTypeDao;
	}
	
	
	public String save(RoomType tipoRoomType) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(tipoRoomType.getUuid())) {
			messageReturn = PrintEntity.TIPO_HABITACION_ID;
		}else if(StringUtils.isBlank(tipoRoomType.getHotelId())) {
			messageReturn = PrintEntity.TIPO_HABITACION_HOTEL_ID;
		}else if(StringUtils.isBlank(tipoRoomType.getDescription())) {
			messageReturn = PrintEntity.TIPO_HABITACION_DESCRIPCION;
		}else if(StringUtils.isBlank(tipoRoomType.getPriceDay().toString())) {
			messageReturn = PrintEntity.TIPO_HABITACION_PRECIO_DIA;
		}else if(StringUtils.isBlank(tipoRoomType.getPriceHour().toString())) {
			messageReturn = PrintEntity.TIPO_HABITACION_PRECIO_HORA;
		}else {
			tipoRoomTypeDao.save(tipoRoomType);
		}
		
		// Si en caso de tener un detalle de precios, entonces no se toma la principal?
		// es una o la otra?
		
		return messageReturn;
	}
	
	
	public String update(RoomType tipoRoomType) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(tipoRoomType.getUuid())) {
			messageReturn = PrintEntity.TIPO_HABITACION_ID;
		}else if(StringUtils.isBlank(tipoRoomType.getHotelId())) {
			messageReturn = PrintEntity.TIPO_HABITACION_HOTEL_ID;
		}else if(StringUtils.isBlank(tipoRoomType.getDescription())) {
			messageReturn = PrintEntity.TIPO_HABITACION_DESCRIPCION;
		}else if(StringUtils.isBlank(tipoRoomType.getPriceDay().toString())) {
			messageReturn = PrintEntity.TIPO_HABITACION_PRECIO_DIA;
		}else if(StringUtils.isBlank(tipoRoomType.getPriceHour().toString())) {
			messageReturn = PrintEntity.TIPO_HABITACION_PRECIO_HORA;
		}else {
			tipoRoomTypeDao.update(tipoRoomType);
		}
		
		// Si en caso de tener un detalle de precios, entonces no se toma la principal?
		// es una o la otra?
		
		return messageReturn;
	}

	public List<RoomType> findByHotelId(String hotelId) throws Exception {
		
		List<RoomType> tipoRoomTypes = null;
		tipoRoomTypes = tipoRoomTypeDao.findByHotelId(hotelId);
		
		return tipoRoomTypes;
	}

}
