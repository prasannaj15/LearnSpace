package JavaPrograms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameVerification {

	public static void main(String[] args) {

		String str = "Prasanna";
		String str1="pras";
		String pattern = "[a-zA-Z0-9._$@]{8,}";
		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(str);
		System.out.println(matcher.matches());
		Matcher matcher1 = compile.matcher(str1);
		System.out.println(matcher1.matches());
		
	}

}
