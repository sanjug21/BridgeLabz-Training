import java.util.*;

public class StringExtremes {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a line of text:");
        String text=sc.nextLine();

        String[] words=splitText(text);
        if (words.length==0) {
            System.out.println("No words found in the text.");
            sc.close();
            return;
        }

        String[][] wordsAndLengths=getWordsAndLengths(words);
        String[] extremes=findShortestAndLongest(wordsAndLengths);

        System.out.println("Shortest string: '"+extremes[0]+ "'");
        System.out.println("Longest string: '"+extremes[1]+ "'");

        sc.close();
    }

    public static String[] findShortestAndLongest(String[][] wordsAndLengths) {
        String shortestWord=wordsAndLengths[0][0];
        String longestWord=wordsAndLengths[0][0];
        int shortestLength=Integer.parseInt(wordsAndLengths[0][1]);
        int longestLength=shortestLength;

        for (int i=1;i<wordsAndLengths.length;i++) {
            int currentLength=Integer.parseInt(wordsAndLengths[i][1]);
            if (currentLength<shortestLength) {
                shortestLength=currentLength;
                shortestWord=wordsAndLengths[i][0];
            }
            if (currentLength>longestLength) {
                longestLength=currentLength;
                longestWord=wordsAndLengths[i][0];
            }
        }
        return new String[]{shortestWord, longestWord};
    }


    public static String[] splitText(String text) {
        ArrayList<String> wordList=new ArrayList<>();
        StringBuilder currentWord=new StringBuilder();
        for (int i=0;i<text.length();i++) {
            if (Character.isWhitespace(text.charAt(i))) {
                if (currentWord.length()>0) wordList.add(currentWord.toString());
                currentWord.setLength(0);
            } else currentWord.append(text.charAt(i));
        }
        if (currentWord.length()>0) wordList.add(currentWord.toString());
        return wordList.toArray(new String[0]);
    }

    public static int findStringLength(String str) {
        int i=0; 
        try { 
            for(;;i++)str.charAt(i); 
        } catch (Exception e) {} 
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