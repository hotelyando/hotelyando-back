package co.com.hotelyando.database.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "hotelyando.reserva")
public class Reserva {
	
	private Integer reservaId;
	private Integer hotelId;
	private String fechaReserva;
	private String fechaIngreso;
	private String fechaSalida;
	private Integer cantidadAdultos;
	private Integer cantidadNinos;
	private Boolean pagoTotal;
	private Double adelantaPago;
	private Persona persona;
}
