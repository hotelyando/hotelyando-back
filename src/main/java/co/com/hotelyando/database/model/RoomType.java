package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "roomtype")
public class RoomType implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
	 
	@Id
	private String uuid;
	private String hotelId;
	private String description;
	private Double priceDay;
	private Double priceHour;
	private List<PriceDetails> priceDetails; 
	
	
	@Data
	public static class PriceDetails{
		
		private String day;
		private Double priceDay;
		private Double priceHour;
		private String startTime;
		private String endTime;
		private Boolean holiday;
		
	}
}
