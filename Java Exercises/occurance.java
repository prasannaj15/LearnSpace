package JavaPrograms;

public class occurance {
	
public static void main(String[] args) {
	
	String a = "You have no choice other than following me!";
	int len = a.length();
	System.out.println(len);
	int count = 0;
	
	char[] myArray = a.toCharArray();
	
	for (int i = 0; i < myArray.length; i++) {
		if (myArray[i]=='o') {
			count=count+1;
		}
	}
	System.out.println(count);
}

}
