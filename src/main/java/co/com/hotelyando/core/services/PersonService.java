package co.com.hotelyando.core.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.com.hotelyando.core.utilities.PrintEntity;
import co.com.hotelyando.database.dao.PersonDao;
import co.com.hotelyando.database.model.Person;

@Service
public class PersonService {

	private final PersonDao personDao;
	
	
	public PersonService(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	
	public Person findByDocumentTypeAndDocument(String typeDocument, String documentNumber) throws Exception {
		
		Person person = null;
		person = personDao.findByDocumentTypeAndDocument(typeDocument, documentNumber);
		
		return person;
	}

	public String save(Person person) throws Exception {
		
		String messagesReturn = "";
		
		if(StringUtils.isBlank(person.getCellPhone())) {
			messagesReturn = PrintEntity.PERSONA_CELULAR;
		}else if(StringUtils.isBlank(person.getEmail())) {
			messagesReturn = PrintEntity.PERSONA_CORREO_ELECTRONICO;
		}else if(StringUtils.isBlank(person.getAddress())) {
			messagesReturn = PrintEntity.PERSONA_DIRECCION;
		}else if(StringUtils.isBlank(person.getBirthdate())) {
			messagesReturn = PrintEntity.PERSONA_FECHA_NACIMIENTO;
		}else if(StringUtils.isBlank(person.getName())) {
			messagesReturn = PrintEntity.PERSONA_NOMBRE_COMPLETO;
		}else if(StringUtils.isBlank(person.getDocument())) {
			messagesReturn = PrintEntity.PERSONA_NUMERO_DOCUMENTO;
		}else if(StringUtils.isBlank(person.getPhone())) {
			messagesReturn = PrintEntity.PERSONA_TELEFONO_FIJO;
		}else if(StringUtils.isBlank(person.getDocumentType().getCode())) {
			messagesReturn = PrintEntity.PERSONA_TIPO_DOCUMENTO;
		}else {
			personDao.save(person);
		}
		
		//Validar que la persona aun no existe
		//validar formato de correo electronico
		//VAlidar fechas
		
		return messagesReturn;
	}
	
	
	public String update(Person person) throws Exception {
		
		String messagesReturn = "";
		
		if(StringUtils.isBlank(person.getCellPhone())) {
			messagesReturn = PrintEntity.PERSONA_CELULAR;
		}else if(StringUtils.isBlank(person.getEmail())) {
			messagesReturn = PrintEntity.PERSONA_CORREO_ELECTRONICO;
		}else if(StringUtils.isBlank(person.getAddress())) {
			messagesReturn = PrintEntity.PERSONA_DIRECCION;
		}else if(StringUtils.isBlank(person.getBirthdate())) {
			messagesReturn = PrintEntity.PERSONA_FECHA_NACIMIENTO;
		}else if(StringUtils.isBlank(person.getName())) {
			messagesReturn = PrintEntity.PERSONA_NOMBRE_COMPLETO;
		}else if(StringUtils.isBlank(person.getDocument())) {
			messagesReturn = PrintEntity.PERSONA_NUMERO_DOCUMENTO;
		}else if(StringUtils.isBlank(person.getPhone())) {
			messagesReturn = PrintEntity.PERSONA_TELEFONO_FIJO;
		}else if(StringUtils.isBlank(person.getDocumentType().getCode())) {
			messagesReturn = PrintEntity.PERSONA_TIPO_DOCUMENTO;
		}else {
			personDao.update(person);
		}
		
		//Validar que la persona aun no existe
		//validar formato de correo electronico
		//VAlidar fechas
		
		return messagesReturn;
	}

	public Person findByDocument(String documentNumber) throws Exception {
		
		Person person = null;
		person = personDao.findByDocument(documentNumber);
		
		return person;
	}

}
