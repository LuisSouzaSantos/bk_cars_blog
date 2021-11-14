package br.com.supersim.blog.utils;

public class Utils {
	
	private final static String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+
										"abcdefghijklmnopqrstuvxyz"+
										"0123456789";
	
	
	public static String randomAlphaNumericString(int lenght) {
		if(lenght < 1) { throw new IllegalArgumentException("The lenght must be greater than 0");}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		while(lenght-- != 0) {
			int index = (int) (ALPHA_NUMERIC_STRING.length() * Math.random()); 
			stringBuilder.append(ALPHA_NUMERIC_STRING.charAt(index));
		}
		
		return stringBuilder.toString();
	}
	
	public static String sanitize(String value) {
		if(value == null) { return null; }
		
		return value.trim();
	}
	
}
