package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PaisService;
import co.com.hotelyando.database.model.Pais;

@Service
public class PaisBusiness {
	
	private final PaisService paisService;
	
	public PaisBusiness(PaisService paisService) {
		this.paisService = paisService;
	}
	
	public String registrarPais(Pais pais) {
		
		String retornoMensaje = "";
		
		try {
			
			retornoMensaje = paisService.registrarPais(pais);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
		
	}

}
