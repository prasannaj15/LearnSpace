package JavaPrograms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailVerification {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String email="balaji.chandrasekaran@testleaf.com";
		String email1="balaji.c@tunatap.co.uk";
		String email2="naveen e@tl.com";
		String regex="[a-zA-z0-9._]+@[a-z0-9]+.[a-z.]{2,}";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(email);
		Matcher matcher2 = compile.matcher(email1);
		Matcher matcher3 = compile.matcher(email2);
		System.out.println(matcher.matches());
		System.out.println(matcher2.matches());
		System.out.println(matcher3.matches());
		
	}

}
