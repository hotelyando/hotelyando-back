package co.com.hotelyando.database.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class DocumentType implements Serializable{

	private static final long serialVersionUID = 5171507945605048441L;
	
	private String code;
	private String description;
	
}
