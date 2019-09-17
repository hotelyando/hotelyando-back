package co.com.hotelyando.core.services;

import java.util.List;

import co.com.hotelyando.database.model.Paquete;

public interface IPaqueteService {
	
	String registrarPaquete(Paquete paquete);
	List<Paquete> consultarPaquetesPorHotel(Integer hotelId);
	Paquete consultarPaquetePorHotel(Integer hotelId, Integer paqueteId);

}
