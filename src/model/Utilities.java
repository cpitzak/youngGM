package model;

public class Utilities {
	
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

}
