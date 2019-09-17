package co.com.hotelyando.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.hotelyando.database.dao.IEmpleadoDao;
import co.com.hotelyando.database.model.Empleado;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	private IEmpleadoDao iEmpleadoDao;
	
	@Override
	public String registrarEmpleado(Empleado empleado) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = iEmpleadoDao.registrarEmpleado(empleado);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
	}

	
	@Override
	public List<Empleado> consultarEmpleadosPorHotel(Integer hotelId) {
		
		List<Empleado> empleados = null;
		
		try {
			
			empleados = iEmpleadoDao.consultarEmpleadosPorHotel(hotelId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return empleados;
	}

	@Override
	public Empleado consultarEmpleadoPorHotel(Integer hotelId, Integer empleadoId) {
		
		Empleado empleado = null;
		
		try {
			
			empleado = iEmpleadoDao.consultarEmpleadoPorHotel(hotelId, empleadoId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return empleado;
	}

}
