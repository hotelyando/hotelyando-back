package co.com.hotelyando.database.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.reserva")
public class Reserva implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String reservaId;
	private String hotelId;
	private String fechaReserva;
	private String fechaIngreso;
	private String fechaSalida;
	private Integer cantidadAdultos;
	private Integer cantidadNinos;
	private Boolean pagoTotal;
	private Double adelantaPago;
	private Persona persona;
}
