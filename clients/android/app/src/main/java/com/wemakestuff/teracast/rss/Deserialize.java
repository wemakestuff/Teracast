package com.wemakestuff.teracast.rss;

public class Deserialize {

	private static final String TRUE  = "TRUE";
	private static final String FALSE = "FALSE";
	private static final String YES   = "YES";
	private static final String Y     = "Y";
	private static final String NO    = "NO";
	private static final String N     = "N";

	public static Boolean fromBoolean(String input) {
		input = input.trim();
		if (YES.equalsIgnoreCase(input) || Y.equalsIgnoreCase(input) || TRUE.equalsIgnoreCase(input))
			return true;
		else if (NO.equalsIgnoreCase(input) || N.equalsIgnoreCase(input) || FALSE.equalsIgnoreCase(input))
			return false;

		return null;
	}
}
