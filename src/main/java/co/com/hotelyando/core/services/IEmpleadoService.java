package co.com.hotelyando.core.services;

import java.util.List;

import co.com.hotelyando.database.model.Empleado;

public interface IEmpleadoService {
	
	String registrarEmpleado(Empleado empleado);
	List<Empleado> consultarEmpleadosPorHotel(Integer hotelId);
	Empleado consultarEmpleadoPorHotel(Integer hotelId, Integer empleadoId);

}
