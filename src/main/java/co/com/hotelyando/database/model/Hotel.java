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
	private String email;
	private String logo;
	private String altitude;
	private String latitude;
	private List<String> socialNetworks;
	private String phone;
	private String cellPhone;
	//private Plan plan;
	private Boolean state;
	//private List<BranchOffice> branchOffices;
	private Parameterize parameterize;
	
	@Data
	static class Parameterize {
		
		private String dataInformation;
		private Boolean payingTaxes;
		private Double taxPercentage;
		private String checkIn;
		private String checkOut;
		private Integer timeReservation;
		
	}

}
