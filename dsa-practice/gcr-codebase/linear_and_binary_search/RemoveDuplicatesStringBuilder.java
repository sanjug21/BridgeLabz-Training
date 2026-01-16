import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RemoveDuplicatesStringBuilder {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String s = sc.nextLine();
        
        String result = removeDuplicates(s);
        
        System.out.println("Original String is: " + s);
        System.out.println("String after removing duplicates: " + result);
        sc.close();
    }

    public static String removeDuplicates(String str) {
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!set.contains(c)) {
                sb.append(c);
                set.add(c);
            }
        }
        return sb.toString();
    }
}