import java.util.*;

public class CharInspector {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string to inspect:");
        String text=sc.nextLine();

        String[][] analysis=analyzeString(text);
        displayTable(analysis);

        sc.close();
    }

    public static String checkCharType(char c) {
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

    public static String[][] analyzeString(String text) {
        String[][] result=new String[text.length()][2];
        for (int i=0;i<text.length();i++) {
            char c=text.charAt(i);
            result[i][0]=String.valueOf(c);
            result[i][1]=checkCharType(c);
        }
        return result;
    }

    public static void displayTable(String[][] data) {
        System.out.println("\nCharacter | Type");
        for (String[] row:data) {
            System.out.println(row[0] + " | " + row[1]);
        }
    }
}