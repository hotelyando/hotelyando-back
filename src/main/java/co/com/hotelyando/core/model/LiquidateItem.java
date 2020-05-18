package co.com.hotelyando.core.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class LiquidateItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<QuantityItem> items;
	private Double tax;
	private Double discount;
	private Double total;
	
	@Data
	public static class QuantityItem {
		
		private String itemUuid;
		private Integer quantity;
	}
	
}
