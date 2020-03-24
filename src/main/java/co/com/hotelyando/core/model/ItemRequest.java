package co.com.hotelyando.core.model;

import java.io.Serializable;

import co.com.hotelyando.database.model.Item;
import lombok.Data;

@Data
public class ItemRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Item item;
	private Integer stockQuantity;
	private String document;
	private String documentType;

	
	
}
