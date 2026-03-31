import java.util.*;

public class WordAnalyzer {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a line of text:");
        String text=sc.nextLine();

        String[] words=splitText(text);
        String[][] wordsAndLengths=getWordsAndLengths(words);

        System.out.println("\n--- Word Analysis ---");
        System.out.println("Word\t\t| Length");
        System.out.println("----------------|--------");
        for (String[] row:wordsAndLengths) {
            // Convert length back to int for display as requested
            int length=Integer.parseInt(row[1]);
            System.out.printf("%-15s | %d%n", row[0], length);
        }

        sc.close();
    }

    public static String[] splitText(String text) {
        if (text==null||text.isEmpty()) return new String[0];
        
        ArrayList<String> wordList=new ArrayList<>();
        StringBuilder currentWord=new StringBuilder();
        for (int i=0;i<text.length();i++) {
            char c=text.charAt(i);
            if (Character.isWhitespace(c)) {
                if (currentWord.length()>0) {
                    wordList.add(currentWord.toString());
                    currentWord.setLength(0);
                }
            } else {
                currentWord.append(c);
            }
        }
        if (currentWord.length()>0) wordList.add(currentWord.toString());
        return wordList.toArray(new String[0]);
    }

    public static int findStringLength(String str) {
        int i=0;
        try {
            for (;;i++) str.charAt(i);
        } catch (StringIndexOutOfBoundsException e) {
            // Expected
        }
        return i;
    }

    public static String[][] getWordsAndLengths(String[] words) {
        String[][] result=new String[words.length][2];
        for (int i=0;i<words.length;i++) {
            result[i][0]=words[i];
            result[i][1]=String.valueOf(findStringLength(words[i]));
        }
        return result;
    }
}