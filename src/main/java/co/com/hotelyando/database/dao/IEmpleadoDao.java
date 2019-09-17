package co.com.hotelyando.database.dao;

import java.util.List;

import co.com.hotelyando.database.model.Empleado;

public interface IEmpleadoDao {
	
	String registrarEmpleado(Empleado empleado) throws Exception;
	List<Empleado> consultarEmpleadosPorHotel(Integer hotelId) throws Exception;
	Empleado consultarEmpleadoPorHotel(Integer hotelId, Integer empleadoId) throws Exception;

}
