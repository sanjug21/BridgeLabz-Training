import java.util.Scanner;

public class ConcatenateStringsStringBuffer {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of strings: ");
        int n = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by nextInt()
        
        String[] strings = new String[n];
        System.out.println("Enter " + n + " strings: ");
        for (int i = 0; i < n; i++) {
            strings[i] = sc.nextLine();
        }
        
        String result = concatenateArray(strings);
        System.out.println("Concatenated String: " + result);
        sc.close();
    }

    public static String concatenateArray(String[] arr) {
        // Using StringBuffer for thread-safe, mutable string operations
        StringBuffer sb = new StringBuffer();
        
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }
}
