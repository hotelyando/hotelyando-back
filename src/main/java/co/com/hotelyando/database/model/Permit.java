package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Permit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String name;
	private String method;
	
	

}
