package org.xmlportletfactory.templates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
	
	public String toLowerCaseHyphen(String s) {
		
		char[] input = s.replaceAll("[ _]","").toCharArray();
		
		StringBuilder output = new StringBuilder();
	
		for (int i = 0; i < input.length; i++) {
			char c = input[i];
			if(Character.isLowerCase(c)) {
				output.append(c);
			} else {
				if(i > 0 && Character.isLowerCase(input[i-1])) {
					output.append("-");
				}
				output.append(Character.toLowerCase(c));
			}
		}
		
		String result = output.toString();
		
		return result;
	}

	public String toSnakeCase(String name) {
		
		Matcher m = Pattern.compile("(?<=[a-z])[A-Z]").matcher(name);

		StringBuffer sb = new StringBuffer();
		while (m.find()) {
		    m.appendReplacement(sb, "_"+m.group().toLowerCase());
		}
		m.appendTail(sb);
		
		return sb.toString();
	}

}
