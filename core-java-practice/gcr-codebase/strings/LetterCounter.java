import java.util.*;

public class LetterCounter {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String text=sc.nextLine();

        int[] counts=countVowelsAndConsonants(text);

        System.out.println("Vowels count: " + counts[0]);
        System.out.println("Consonants count: " + counts[1]);

        sc.close();
    }

    public static String checkCharType(char c) {
        // Convert to lowercase using ASCII logic
        if (c>='A'&&c<='Z') {
            c=(char)(c+32);
        }

        if (c>='a'&&c<='z') {
            if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        } else {
            return "Not a Letter";
        }
    }

    public static int[] countVowelsAndConsonants(String text) {
        int vowelCount=0;
        int consonantCount=0;
        for (int i=0;i<text.length();i++) {
            String type=checkCharType(text.charAt(i));
            if (type.equals("Vowel")) vowelCount++;
            else if (type.equals("Consonant")) consonantCount++;
        }
        return new int[]{vowelCount, consonantCount};
    }
}