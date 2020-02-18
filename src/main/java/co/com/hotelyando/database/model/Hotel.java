package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotel")
public class Hotel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String nit;
	private String address;
	private String name;
	private String altitude;
	private String latitude;
	private List<String> socialNetworks;
	private String phone;
	private String cellPhone;
	private Plan plan;
	private List<BranchOffice> sucursales;
	

}
