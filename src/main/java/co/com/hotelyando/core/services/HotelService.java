package co.com.hotelyando.core.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.database.dao.HotelDao;
import co.com.hotelyando.database.model.Hotel;

@Service
public class HotelService {

	
	private final HotelDao hotelDao;
	
	public HotelService(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}
	
	
	public String save(Hotel hotel) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(hotel.getUuid())) {
			messageReturn = PrintEntity.HOTEL_ID;
		}else if(StringUtils.isBlank(hotel.getAddress())) {
			messageReturn = PrintEntity.HOTEL_ADDRESS;
		}else if(StringUtils.isBlank(hotel.getAltitude())) {
			messageReturn = PrintEntity.HOTEL_ALTITUDE;
		}else if(StringUtils.isBlank(hotel.getCellPhone())) {
			messageReturn = PrintEntity.HOTEL_CELL_PHONE;
		}else if(StringUtils.isBlank(hotel.getLatitude())) {
			messageReturn = PrintEntity.HOTEL_LATITUD;
		}else if(StringUtils.isBlank(hotel.getName())) {
			messageReturn = PrintEntity.HOTEL_NAME;
		}else if(StringUtils.isBlank(hotel.getNit())) {
			messageReturn = PrintEntity.HOTEL_NIT;
		}else if(StringUtils.isBlank(hotel.getPhone())) {
			messageReturn = PrintEntity.HOTEL_PHONE;
		}else if(hotel.getPlan() == null) {
			messageReturn = PrintEntity.HOTEL_PLAN;
		}else if(hotel.getSocialNetworks() == null) {
			messageReturn = PrintEntity.HOTEL_SOCIAL_NETWORKS;
		}else if(hotel.getSucursales() == null) {
			messageReturn = PrintEntity.HOTEL_SUCURSAL;
		}else {
			hotelDao.save(hotel);
		}
		
		//Se debería validar la existencia del hotel.
		//Deberia tener ciudad y pais o esa va en sucursales?
		
		return messageReturn;
	}
	
	public String update(Hotel hotel) throws Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(hotel.getUuid())) {
			messageReturn = PrintEntity.HOTEL_ID;
		}else if(StringUtils.isBlank(hotel.getAddress())) {
			messageReturn = PrintEntity.HOTEL_ADDRESS;
		}else if(StringUtils.isBlank(hotel.getAltitude())) {
			messageReturn = PrintEntity.HOTEL_ALTITUDE;
		}else if(StringUtils.isBlank(hotel.getCellPhone())) {
			messageReturn = PrintEntity.HOTEL_CELL_PHONE;
		}else if(StringUtils.isBlank(hotel.getLatitude())) {
			messageReturn = PrintEntity.HOTEL_LATITUD;
		}else if(StringUtils.isBlank(hotel.getName())) {
			messageReturn = PrintEntity.HOTEL_NAME;
		}else if(StringUtils.isBlank(hotel.getNit())) {
			messageReturn = PrintEntity.HOTEL_NIT;
		}else if(StringUtils.isBlank(hotel.getPhone())) {
			messageReturn = PrintEntity.HOTEL_PHONE;
		}else if(hotel.getPlan() == null) {
			messageReturn = PrintEntity.HOTEL_PLAN;
		}else if(hotel.getSocialNetworks() == null) {
			messageReturn = PrintEntity.HOTEL_SOCIAL_NETWORKS;
		}else if(hotel.getSucursales() == null) {
			messageReturn = PrintEntity.HOTEL_SUCURSAL;
		}else {
			hotelDao.update(hotel);
		}
		
		//Se debería validar la existencia del hotel.
		//Deberia tener ciudad y pais o esa va en sucursales?
		
		return messageReturn;
	}

	public Hotel findByUuid(String uuid) throws Exception {
		
		Hotel hotel = null;
		hotel = hotelDao.findByUuid(uuid);
		
		return hotel;
	}

}
