package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "role")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String name;
	private String description;
	private List<Permit> permits;

}
