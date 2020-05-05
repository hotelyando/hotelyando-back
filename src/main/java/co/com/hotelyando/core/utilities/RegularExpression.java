package co.com.hotelyando.core.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class RegularExpression {
	
	private Pattern pattern = null;
    private Matcher matcher = null;
    private Boolean result = null;
	
    
    /*
     * Método que valida los carácteres especiales de un campo de texto
     * Retorna false si cumple la expresión regular
     * @return Boolean
     */
	public boolean validateSpecialCharacters(String text) {
		
		if(StringUtils.isBlank(text)) {
			result = true;
		}else {
			
			pattern = Pattern.compile("[^A-Za-z0-9*]");
		    matcher = pattern.matcher(text);
		    
		    result = matcher.find();
		}
		
	    return result;
		
	}
	
	
	/*
     * Método que valida solo números en un campo de texto
     * Retorna false si cumple la expresión regular
     * @return Boolean
     */
	public boolean validateNumeric(String text) {
		
		result = false;
		
		if(text == null) {
			result = true;
		}else {
			
			pattern = Pattern.compile("[^0-9*]");
		    matcher = pattern.matcher(text.toString());
		    
		    //result = matcher.find();
		}
		
		/*if(!result && text < 0) {
			result = true;
		}*/
		
		return result;
		
	}
	
	
	/*
     * Método que valida el correo electrónico de un campo de texto
     * Retorna false si cumple la expresión regular
     * @return Boolean
     */
	public boolean validateEmail(String text) {
		
		if(StringUtils.isBlank(text)) {
			result = false;
		}else {
			
			pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		    matcher = pattern.matcher(text);
		    
		    result = matcher.find();
		}
	    
	    return !result;
		
	}
	
	
	/*
     * Método que valida el formato de una fecha
     * Retorna false si cumple la expresión regular
     * @return Boolean
     */
	public boolean validateFormatDate(String text) {
		
		if(StringUtils.isBlank(text)) {
			result = false;
		}else {
			
			pattern = Pattern.compile("");
		    matcher = pattern.matcher(text);
		    
		    result = matcher.find();
		}
		
	    return false;
		
	}

}
