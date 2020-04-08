package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "parameter")
public class Parameter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String hotelId;
	private List<ParameterAdmin> parameters;
	
	@Data
	public static class ParameterAdmin {
		
		private String code;
		private String name;
		private String value;
		private Boolean modifiable;
		private Boolean visible;
		
	}
	
	

}
