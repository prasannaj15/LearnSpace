package JavaPrograms;

public class ReverseStringPart {

	public static void main(String[] args) {
		
		String str = "When the world realise it's own mistake, corona will dissolve automatically";
		String[] split = str.split(" ");
		String myString= "";
		String revStr= "";
		int length = split.length;
		
		for (int i = 0; i < length; i++) {
			if (i%2==0) {
				myString = myString+split[i]+" ";
			} else {
				char[] charArray = split[i].toCharArray();
				int length2 = split[i].length();
				for (int j = length2-1; j >=0; j--) {
					revStr=revStr+charArray[j];
				}
				myString = myString+revStr+" ";
				revStr="";
			}
		}
		System.out.println(myString);
	}

}
