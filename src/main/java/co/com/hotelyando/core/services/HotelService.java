package co.com.hotelyando.core.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import co.com.hotelyando.core.utilities.RegularExpression;
import co.com.hotelyando.database.dao.HotelDao;
import co.com.hotelyando.database.model.Hotel;

@Service
public class HotelService {

	@Autowired
	private MessageSource messageSource;
	
	private RegularExpression regularExpression = null;
	
	private final HotelDao hotelDao;
	
	public HotelService(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
		
		regularExpression = new RegularExpression();
	}
	
	
	/*
	 * Método para el registro de un HOTEL
	 * @return String
	 */
	public String save(Hotel hotel) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(hotel.getUuid())) {
			messageReturn = messageSource.getMessage("hotel.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(hotel.getAddress())) {
			messageReturn = messageSource.getMessage("hotel.address", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(hotel.getAltitude())) {
			messageReturn = messageSource.getMessage("hotel.altitude", null, LocaleContextHolder.getLocale());
		}else if(hotel.getCellPhone() == null) {
			messageReturn = messageSource.getMessage("hotel.cell_phone", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateNumeric(hotel.getCellPhone())) {
			messageReturn = messageSource.getMessage("hotel.cell_phone_numeric", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(hotel.getLatitude())) {
			messageReturn = messageSource.getMessage("hotel.latitud", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateSpecialCharacters(hotel.getName())) {
			messageReturn = messageSource.getMessage("hotel.name_character", null, LocaleContextHolder.getLocale());
		}else if(nameValidate(hotel.getName(), false)) {
			messageReturn = messageSource.getMessage("hotel.name_unique", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(hotel.getNit())) {
			messageReturn = messageSource.getMessage("hotel.nit", null, LocaleContextHolder.getLocale());
		}else if(nitValidate(hotel.getNit(), false)) {
			messageReturn = messageSource.getMessage("hotel.nit_unique", null, LocaleContextHolder.getLocale());
		}else if(hotel.getPhone() == null) {
			messageReturn = messageSource.getMessage("hotel.phone", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateNumeric(hotel.getPhone())) {
			messageReturn = messageSource.getMessage("hotel.phone_numeric", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateEmail(hotel.getEmail())) {
			messageReturn = messageSource.getMessage("hotel.email_format", null, LocaleContextHolder.getLocale());
		}else if(hotel.getPlan() == null) {
			messageReturn = messageSource.getMessage("hotel.plan", null, LocaleContextHolder.getLocale());
		}else if(hotel.getSocialNetworks() == null) {
			messageReturn = messageSource.getMessage("hotel.social_networks", null, LocaleContextHolder.getLocale());
		}else if(hotel.getBranchOffices() == null) {
			messageReturn = messageSource.getMessage("hotel.sucursal", null, LocaleContextHolder.getLocale());
		}else {
			hotelDao.save(hotel);
		}
		
		//Se debería validar la existencia del hotel.
		//Deberia tener ciudad y pais o esa va en sucursales?
		
		return messageReturn;
	}
	
	
	/*
	 * Método para la actualización de un HOTEL
	 * @return String
	 */
	public String update(Hotel hotel) throws MongoException, Exception {
		
		String messageReturn = "";
		
		if(StringUtils.isBlank(hotel.getUuid())) {
			messageReturn = messageSource.getMessage("hotel.id", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(hotel.getAddress())) {
			messageReturn = messageSource.getMessage("hotel.address", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(hotel.getAltitude())) {
			messageReturn = messageSource.getMessage("hotel.altitude", null, LocaleContextHolder.getLocale());
		}else if(hotel.getCellPhone() == null) {
			messageReturn = messageSource.getMessage("hotel.cell_phone", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateNumeric(hotel.getCellPhone())) {
			messageReturn = messageSource.getMessage("hotel.cell_phone_numeric", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(hotel.getLatitude())) {
			messageReturn = messageSource.getMessage("hotel.latitud", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateSpecialCharacters(hotel.getName())) {
			messageReturn = messageSource.getMessage("hotel.name_character", null, LocaleContextHolder.getLocale());
		}else if(nameValidate(hotel.getName(), true)) {
			messageReturn = messageSource.getMessage("hotel.name_unique", null, LocaleContextHolder.getLocale());
		}else if(StringUtils.isBlank(hotel.getNit())) {
			messageReturn = messageSource.getMessage("hotel.nit", null, LocaleContextHolder.getLocale());
		}else if(nitValidate(hotel.getNit(), true)) {
			messageReturn = messageSource.getMessage("hotel.nit_unique", null, LocaleContextHolder.getLocale());
		}else if(hotel.getPhone() == null) {
			messageReturn = messageSource.getMessage("hotel.phone", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateNumeric(hotel.getPhone())) {
			messageReturn = messageSource.getMessage("hotel.phone_numeric", null, LocaleContextHolder.getLocale());
		}else if(regularExpression.validateEmail(hotel.getEmail())) {
			messageReturn = messageSource.getMessage("hotel.email_format", null, LocaleContextHolder.getLocale());
		}else if(hotel.getPlan() == null) {
			messageReturn = messageSource.getMessage("hotel.plan", null, LocaleContextHolder.getLocale());
		}else if(hotel.getSocialNetworks() == null) {
			messageReturn = messageSource.getMessage("hotel.social_networks", null, LocaleContextHolder.getLocale());
		}else if(hotel.getBranchOffices() == null) {
			messageReturn = messageSource.getMessage("hotel.sucursal", null, LocaleContextHolder.getLocale());
		}else {
			hotelDao.update(hotel);
		}
		
		//Se debería validar la existencia del hotel.
		//Deberia tener ciudad y pais o esa va en sucursales?
		
		return messageReturn;
	}

	
	/*
	 * Método para retornar la información por uuid
	 * @return Hotel
	 */
	public Hotel findByUuid(String uuid) throws MongoException, Exception {
		
		Hotel hotel = hotelDao.findByUuid(uuid);
		
		return hotel;
	}
	
	
	/*
	 * Método para validar si el nombre existe en la base de datos
	 * @return boolean
	 */
	private boolean nameValidate(String name, Boolean update) throws MongoException, Exception {
		
		Hotel hotel = hotelDao.findByName(name);
		
		if (hotel == null) {
			return false;
		}else if(update && hotel.getName().equals(name)) {
			return false;
		}else {
			return true;
		}
	}
	
	
	/*
	 * Método para validar si el nit existe en la base de datos
	 * @return boolean
	 */
	private boolean nitValidate(String nitHotel, Boolean update) throws MongoException, Exception {
		
		Hotel hotel = hotelDao.findByNit(nitHotel);
		
		if(hotel == null) {
			return false;
		}else if (update && hotel.getNit().equals(nitHotel)){
			return false;
		}else {
			return true;
		}
	}

}
