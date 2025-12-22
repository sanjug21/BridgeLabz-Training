import java.util.*;

public class UniqueCharFinder {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String text=sc.next();

        char[] uniqueChars=findUniqueCharacters(text);
        
        System.out.print("Unique characters: ");
        for (char c:uniqueChars) {
            System.out.print(c+" ");
        }
        System.out.println();
        sc.close();
    }

    public static int getStringLength(String str) {
        int count=0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            return count;
        }
    }

    public static char[] findUniqueCharacters(String text) {
        int len=getStringLength(text);
        char[] temp=new char[len];
        int uniqueCount=0;

        for (int i=0;i<len;i++) {
            char currentChar=text.charAt(i);
            boolean isUnique=true;
            // Check if this character has appeared before
            for (int j=0;j<i;j++) {
                if (text.charAt(j)==currentChar) {
                    isUnique=false;
                    break;
                }
            }
            if (isUnique) {
                temp[uniqueCount++]=currentChar;
            }
        }

        char[] result=new char[uniqueCount];
        System.arraycopy(temp,0,result,0,uniqueCount);
        return result;
    }
}