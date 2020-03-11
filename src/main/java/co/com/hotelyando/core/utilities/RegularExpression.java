package co.com.hotelyando.core.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
	
	private Pattern pattern = null;
    private Matcher matcher = null;
    private Boolean result = null;
	
    
    /*
     * Método que valida los carácteres especiales de un campo de texto
     * @return Boolean
     */
	public boolean validateSpecialCharacters(String text) {
		
		pattern = Pattern.compile("[^A-Za-z0-9*]");
	    matcher = pattern.matcher(text);
	    
	    result = matcher.find();
	    
	    return result;
		
	}
	
	
	/*
     * Método que valida solo números en un campo de texto
     * @return Boolean
     */
	public boolean validateNumeric(String text) {
		
		pattern = Pattern.compile("[^0-9*]");
	    matcher = pattern.matcher(text);
	    
	    result = matcher.find();
	    
	    return result;
		
	}
	
	
	/*
     * Método que valida el correo electrónico de un campo de texto
     * @return Boolean
     */
	public boolean validateEmail(String text) {
		
		pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	    matcher = pattern.matcher(text);
	    
	    result = matcher.find();
	    //Se retorna el valor contrario para guarda el estandar de que devuelva false, cuando aplica la expresion regular.
	    return !result;
		
	}
	
	
	/*
     * Método que valida el formato de una fecha
     * @return Boolean
     */
	public boolean validateFormatDate(String text) {
		
		pattern = Pattern.compile("");
	    matcher = pattern.matcher(text);
	    
	    result = matcher.find();
	    //Se retorna el valor contrario para guarda el estandar de que devuelva false, cuando aplica la expresion regular.
	    return false;
		
	}

}
