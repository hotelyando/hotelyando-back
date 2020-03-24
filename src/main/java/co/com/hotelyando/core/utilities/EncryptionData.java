package co.com.hotelyando.core.utilities;


import org.apache.tomcat.util.codec.binary.Base64;


public class EncryptionData {
	
	public String encript(String text) throws Exception {
		
	    byte[] encrypted = text.getBytes();
	         
	    return Base64.encodeBase64String(encrypted);
	    
	}
	 
	public String decrypt(String text) throws Exception {
		
		String decrypted;
		
	    byte[] encryptedBytes = Base64.decodeBase64(text);
	    
	    decrypted = new String(encryptedBytes);
	    
	    return decrypted;
	
	}

}
