package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "package")
public class PackageHotel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String hotelId;
	private String description;
	private Integer price;
	private Integer time; 
	private List<Item> items; 

}
