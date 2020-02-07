package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.persona")
public class Persona  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String numeroDocumento;
	private String celular;
	private String correoElectronico;
	private String direccion;
	private String fechaNacimiento;
	private String nombreCompleto;
	private String telefonoFijo;
	private TipoDocumento tipoDocumento;

}
