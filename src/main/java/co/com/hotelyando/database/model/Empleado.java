package co.com.hotelyando.database.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.empleado")
public class Empleado {

	private Integer empleadoId;
	private Integer hotelId;
	private List<_cargo> cargos;
	private List<_rol> roles;
	private Persona persona;
}
