package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "country")
public class Country implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	private String uuid;
	private String code;
	private String name;

}
