package co.com.hotelyando.database.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Type implements Serializable{

	private static final long serialVersionUID = 1L;
	private String uuid;
	private String description;

}
