package co.com.hotelyando.database.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Tipo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer tipoId;
	private String descripcion;

}
