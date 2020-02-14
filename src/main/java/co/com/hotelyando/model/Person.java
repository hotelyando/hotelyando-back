package co.com.hotelyando.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.hotelyando.database.model.DocumentType;
import lombok.Data;

@Data
@Document(collection = "person")
public class Person implements Serializable {

  /** Serial id. */
  private static final long serialVersionUID = -3690803153077062394L;

  @Id
  private String uuid;
  private DocumentType documentType;
  private String document;
  private String name;
  private String email;
  private String birthdate;
  private String phone;

}
