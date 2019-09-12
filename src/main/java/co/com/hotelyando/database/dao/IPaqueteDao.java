package co.com.hotelyando.database.dao;

import java.util.List;

import co.com.hotelyando.database.model.Paquete;

public interface IPaqueteDao {
	
	String registrarPaquete(Paquete paquete) throws Exception;
	List<Paquete> consultarPaquetesPorHotel(Integer hotelId) throws Exception;
	Paquete consultarPaquetePorHotel(Integer hotelId, Integer paqueteId) throws Exception;

}
