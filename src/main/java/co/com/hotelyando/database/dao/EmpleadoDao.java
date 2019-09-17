package co.com.hotelyando.database.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.hotelyando.database.model.Empleado;
import co.com.hotelyando.database.repository.IEmpleadoRepository;

@Repository
public class EmpleadoDao implements IEmpleadoDao {

	@Autowired
	private IEmpleadoRepository iEmpleadoRepository;
	
	@Override
	public String registrarEmpleado(Empleado empleado) throws Exception {
		
		iEmpleadoRepository.save(empleado);
		
		return null;
	}

	@Override
	public List<Empleado> consultarEmpleadosPorHotel(Integer hotelId) throws Exception {
		
		List<Empleado> empleados = null;
		
		empleados = iEmpleadoRepository.findByHotelId(hotelId);
		
		return empleados;
	}

	@Override
	public Empleado consultarEmpleadoPorHotel(Integer hotelId, Integer empleadoId) throws Exception {
		
		Empleado empleado = null;
		
		empleado = iEmpleadoRepository.findByHotelIdAndEmpleadoId(hotelId, empleadoId);
		
		return empleado;
	}

}
