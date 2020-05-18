package co.com.hotelyando.core.model;

import java.io.Serializable;
import java.util.List;

import co.com.hotelyando.database.model.Room;
import lombok.Data;

@Data
public class LiquidateRoom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> roomsUuid;
	private String initDate;
	private String endDate;
	private Double tax;
	private Double discount;
	private Double total;
	

}
