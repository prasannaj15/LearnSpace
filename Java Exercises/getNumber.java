package JavaPrograms;

public class getNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String myString = "asd1kdj9ifk8lk7";
		int a=0;
		
		char[] mycharArray = myString.toCharArray();
		int length = myString.length();
		
		for (int i = 0; i < length; i++) {
			
			boolean digit = Character.isDigit(mycharArray[i]);
			if (digit==true) {
				a+=Character.getNumericValue(mycharArray[i]);
			}
	
		}
		System.out.println(a);
	}

}
