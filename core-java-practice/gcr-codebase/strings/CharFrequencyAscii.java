import java.util.*;

public class CharFrequencyAscii {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String text=sc.next();

        String[][] frequencyData=getCharacterFrequency(text);
        
        System.out.println("Character | Frequency");
        for (String[] row:frequencyData) {
            System.out.println(row[0] + " | " + row[1]);
        }
        sc.close();
    }

    public static String[][] getCharacterFrequency(String text) {
        int[] frequency=new int[256];
        for (int i=0;i<text.length();i++) {
            frequency[text.charAt(i)]++;
        }

        // Here count the frequency of characters
        int distinctCount=0;
        for (int i=0;i<256;i++) {
            if (frequency[i]>0) distinctCount++;
        }

        String[][] result=new String[distinctCount][2];
        int index=0;
        for (int i=0;i<256;i++) {
            if (frequency[i]>0) {
                result[index][0]=String.valueOf((char)i);
                result[index][1]=String.valueOf(frequency[i]);
                index++;
            }
        }
        return result;
    }
}