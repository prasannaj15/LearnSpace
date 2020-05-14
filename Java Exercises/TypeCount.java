package JavaPrograms;

public class TypeCount {

	public static void main(String[] args) {

		String str = "klsdkf23823098kjskj kjlksjd 988 jsk 23";
		int digit = 0;
		int letter = 0;
		int space=0;
		char[] charArray = str.toCharArray();
		int length = str.length();

		for (int i = 0; i < length; i++) {
			
			if (Character.isDigit(charArray[i]) == true) {
				digit = digit + 1;
			} else if (Character.isLetter(charArray[i]) == true) {
				letter = letter + 1;
			} else if (Character.isWhitespace(charArray[i]) == true) {
				space = space + 1;
			}
		}
		
		System.out.println("Total no of digits is : " + digit);
		System.out.println("Total no of letters is : " + letter);
		System.out.println("Total no of spaces is : " + space);
	}

}
