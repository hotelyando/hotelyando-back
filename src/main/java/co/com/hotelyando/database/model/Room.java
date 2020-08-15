package co.com.hotelyando.database.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "room")
public class Room implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;
	private String hotelId;
	private String id;
	private String description;
	private Integer floor;
	private Integer area;
	private Integer maximumPersons;
	private Integer numberBeds;
	private Boolean freeParking;
	private String roomTypeUuid;
	private List<ItemInRoom> items;
	private List<String> comforts;
	private String state;
	private Integer score;
	private List<Comment> comments;
	
	@Data
	public static class ItemInRoom {
		
		private String itemId;
		private String description;
		private Integer quantity;
		private Boolean requireCheck;
		
	}
	
	@Data
	public static class Comment {
		
		private String uuid;
		private String description;
		private String userId;
		private String date;
		private Integer score;
		private String comment;
	}
}
