package co.com.hotelyando.core.utilities;


import org.apache.tomcat.util.codec.binary.Base64;


public class EncryptionData {
	
	public String code(String text) throws Exception {
		
	    byte[] encrypted = text.getBytes();
	         
	    return Base64.encodeBase64String(encrypted);
	    
	}
	 
	public String decipher(String text) throws Exception {
		
		String decrypted;
		
	    byte[] encryptedBytes = Base64.decodeBase64(text);
	    
	    decrypted = new String(encryptedBytes);
	    
	    return decrypted;
	
	}
	
	public String encript(String text, String hashType) {
		
		try {
			
			java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
			
			byte[] array = md.digest(text.getBytes());
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
				//sb.append(Integer.toString(array[i]));
			}
			
			return sb.toString();
			
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
}
