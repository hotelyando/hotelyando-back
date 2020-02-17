package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class PriceDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String day;
	private Double priceDay;
	private Double priceHour;
	private Integer startTime;
	private Integer endTime;
	
}
