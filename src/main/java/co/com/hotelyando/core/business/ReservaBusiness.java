package co.com.hotelyando.core.business;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.ReservaService;
import co.com.hotelyando.core.utilities.Genericos;
import co.com.hotelyando.database.model.Reserva;
import co.com.hotelyando.database.model.Usuario;

@Service
public class ReservaBusiness {

private final ReservaService reservaService;
	
	private Genericos<Usuario> genericos;
	private Usuario objetoUsuario;
	
	public ReservaBusiness(ReservaService reservaService) {
		this.reservaService = reservaService;
		
		genericos = new Genericos<>();
	}
	
	public String registrarReserva(Reserva reserva, String usuario) {
		
		String retornoMensaje = "";
		
		try {
			retornoMensaje = reservaService.registrarReserva(reserva);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return retornoMensaje;
	}

	public List<Reserva> consultarReservasPorHotel(String usuario) {
		
		List<Reserva> reservas = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			reservas = reservaService.consultarReservasPorHotel(objetoUsuario.getHotelId());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return reservas;
	}

	public Reserva consultarReservaPorHotel(String usuario, Integer reservaId) {
		
		Reserva reserva = null;
		
		try {
			
			objetoUsuario = genericos.convertirJsonAObjeto(usuario);
			
			reserva = reservaService.consultarReservaPorHotel(objetoUsuario.getHotelId(), reservaId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return reserva;
	}

}
