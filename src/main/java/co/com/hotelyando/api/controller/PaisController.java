package co.com.hotelyando.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.PaisBusiness;
import co.com.hotelyando.database.model.Pais;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Pais")
public class PaisController {

	private final PaisBusiness paisBusiness;
	
	public PaisController(PaisBusiness paisBusiness) {
		this.paisBusiness = paisBusiness;
	}
	
	@PostMapping("/pais")
	public ResponseEntity<String> registrarPais(@RequestBody Pais pais){
		
		String retornoRespuesta = paisBusiness.registrarPais(pais);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
}
