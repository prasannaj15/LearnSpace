package JavaPrograms;

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a = "MADAM";
		String c = "";
		int len = a.length();
		
		
		for (int i = len-1; i >= 0; i--) {
			c = c + a.charAt(i);
		}
		if (a.equals(c)) {
			System.out.println("its a palindrome");
		} else {
			System.out.println("its not a palindrome");
		}
		
	}

}
