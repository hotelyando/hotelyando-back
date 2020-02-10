package co.com.hotelyando.core.business;

import org.springframework.stereotype.Service;

import co.com.hotelyando.core.services.PaisService;
import co.com.hotelyando.core.utilities.Utilidades;
import co.com.hotelyando.database.model.Pais;

@Service
public class PaisBusiness {
	
	private final PaisService paisService;
	private Utilidades utilidades = null;
	
	public PaisBusiness(PaisService paisService) {
		this.paisService = paisService;
		utilidades = new Utilidades();
	}
	
	public String registrarPais(Pais pais) {
		
		String retornoMensaje = "";
		
		try {
			
			pais.setPaisId(utilidades.generadorId());
			
			retornoMensaje = paisService.registrarPais(pais);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return retornoMensaje;
		
	}

}
