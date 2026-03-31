
import java.util.*;

public class ManualCharExtraction {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter text:");
        String text=sc.next();

        char[] manualArray=extractCharacters(text);
        char[] builtInArray=text.toCharArray();

        System.out.println("Manual Array: " + Arrays.toString(manualArray));
        System.out.println("Built-in Array: " + Arrays.toString(builtInArray));

        boolean result=compareCharArrays(manualArray, builtInArray);
        System.out.println("Arrays are equal: " + result);

        sc.close();
    }

    public static char[] extractCharacters(String str) {
        char[] chars=new char[str.length()];
        for (int i=0;i<str.length();i++) {
            chars[i]=str.charAt(i);
        }
        return chars;
    }

    public static boolean compareCharArrays(char[] arr1, char[] arr2) {
        if (arr1.length!=arr2.length) {
            return false;
        }
        for (int i=0;i<arr1.length;i++) {
            if (arr1[i]!=arr2[i]) {
                return false;
            }
        }
        return true;
    }
}