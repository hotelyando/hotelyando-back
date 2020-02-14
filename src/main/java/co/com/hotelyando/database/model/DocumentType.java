package co.com.hotelyando.database.model;

public enum DocumentType {

	CC("CC", "Cédula de ciudadanía"), NIT("NIT", "NIT");

	private String code;
	private String description;

	private DocumentType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
