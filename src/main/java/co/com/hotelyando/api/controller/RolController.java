package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.RolBusiness;
import co.com.hotelyando.database.model.Rol;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "Rol")
public class RolController {
	
	private final RolBusiness rolBusiness;
	
	public RolController(RolBusiness rolBusiness) {
		this.rolBusiness = rolBusiness;
	}
	
	@PostMapping("/rol")
	public ResponseEntity<String> registrarRol(@RequestBody Rol rol, @RequestHeader Map<String, String> headers){
		
		String retornoRespuesta = rolBusiness.registrarRol(rol);
		
		if(StringUtils.isEmpty(retornoRespuesta)) {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(retornoRespuesta, HttpStatus.OK);
		}
	}
	
	@GetMapping("/rol")
	public ResponseEntity<List<Rol>> consultarRolsPorHotel(@RequestHeader Map<String, String> headers){
		
		List<Rol> rols = rolBusiness.consultarRoles();
		
		if(rols == null){
			return new ResponseEntity<List<Rol>>(rols, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Rol>>(rols, HttpStatus.OK);
		}
	}

}
