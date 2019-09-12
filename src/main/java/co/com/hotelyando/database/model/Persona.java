package co.com.hotelyando.database.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.persona")
public class Persona {
	
	private String numeroDocumento;
	private String celular;
	private String correoElectronico;
	private String direccion;
	private String fechaNacimiento;
	private String nombreCompleto;
	private String telefonoFijo;
	private _tipoDocumento tipoDocumento;

}
