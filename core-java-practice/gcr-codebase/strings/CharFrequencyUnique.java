import java.util.*;

public class CharFrequencyUnique {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter a string:");
        String text=scanner.next();

        String[][] frequencyData=getFrequencyUsingUnique(text);

        System.out.println("Character | Frequency");
        for (String[] row:frequencyData) {
            System.out.println(row[0] + " | " + row[1]);
        }
        scanner.close();
    }

    public static char[] findUniqueCharacters(String text) {
        char[] temp=new char[text.length()];
        int uniqueCount=0;

        for (int i=0;i<text.length();i++) {
            char currentChar=text.charAt(i);
            boolean seen=false;
            for (int j=0;j<uniqueCount;j++) {
                if (temp[j]==currentChar) {
                    seen=true;
                    break;
                }
            }
            if (!seen) {
                temp[uniqueCount++]=currentChar;
            }
        }
        
        char[] result=new char[uniqueCount];
        for (int i=0;i<uniqueCount;i++) {
            result[i]=temp[i];
        }
        return result;
    }

    public static String[][] getFrequencyUsingUnique(String text) {
        char[] uniqueChars=findUniqueCharacters(text);
        String[][] result=new String[uniqueChars.length][2];

        for (int i=0;i<uniqueChars.length;i++) {
            char c=uniqueChars[i];
            int count=0;
            // Count occurrences in original text
            for (int j=0;j<text.length();j++) {
                if (text.charAt(j)==c) {
                    count++;
                }
            }
            result[i][0]=String.valueOf(c);
            result[i][1]=String.valueOf(count);
        }
        return result;
    }
}