package co.com.hotelyando.core.model;

import java.io.Serializable;

import co.com.hotelyando.database.model.Employee;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.User;
import lombok.Data;

@Data
public class DataEmployee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Person person;
	private User user;
	private Employee employee;
	
}
