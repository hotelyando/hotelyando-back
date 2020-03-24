package co.com.hotelyando.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.hotelyando.core.business.PackageBusiness;
import co.com.hotelyando.core.utilities.PrintVariable;
import co.com.hotelyando.core.utilities.Utilities;
import co.com.hotelyando.database.model.PackageHotel;
import co.com.hotelyando.database.model.User;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(tags = "PackageHotel")
public class PackageController {
	
	private final PackageBusiness packageBusiness;
	
	private Utilities utilities;
	private User user;
	
	public PackageController(PackageBusiness packageBusiness) {
		this.packageBusiness = packageBusiness;
		
		utilities = new Utilities();
	}
	
	@PostMapping("/package")
	public ResponseEntity<String> save(@RequestBody PackageHotel packageHotel, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		String messageReturn = packageBusiness.save(packageHotel, user);

		if(StringUtils.isEmpty(messageReturn)) {
			return new ResponseEntity<String>(messageReturn, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>(messageReturn, HttpStatus.OK);
		}
	}
	
	@GetMapping("/package/{packageId}")
	public ResponseEntity<PackageHotel> consultarPaquetePorHotel(@PathVariable String packageId, @RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		PackageHotel packageHotel = packageBusiness.findByHotelIdAndUuid(user, packageId);
			
		if(packageHotel == null) {
			return new ResponseEntity<PackageHotel>(packageHotel, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<PackageHotel>(packageHotel, HttpStatus.OK);
		}
	}
	
	@GetMapping("/package")
	public ResponseEntity<List<PackageHotel>> findByHotelId(@RequestHeader Map<String, String> headers){
		
		user = utilities.returnTenant(headers, PrintVariable.TOKEN_HEADER);
			
		List<PackageHotel> packageHotels = packageBusiness.findByHotelId(user);
			
		if(packageHotels == null) {
			return new ResponseEntity<List<PackageHotel>>(packageHotels, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<PackageHotel>>(packageHotels, HttpStatus.OK);
		}
	}


}
