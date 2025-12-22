import java.util.*;

public class TextSplitter {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a line of text to split:");
        String text=sc.nextLine();

        String[] manualSplit=splitText(text);
        // Use regex \\s+ to match one or more whitespace characters, similar to manual method
        String[] builtinSplit=text.trim().split("\\s+");

        System.out.println("Manual Split Result:     "+Arrays.toString(manualSplit));
        System.out.println("Built-in split() Result: "+Arrays.toString(builtinSplit));

        boolean areEqual=Arrays.equals(manualSplit,builtinSplit);
        System.out.println("Do the results match? "+areEqual);

        sc.close();
    }

    public static String[] splitText(String text) {
        if (text==null||text.isEmpty()) {
            return new String[0];
        }
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
        // Add the last word if it exists
        if (currentWord.length()>0) {
            wordList.add(currentWord.toString());
        }

        return wordList.toArray(new String[0]);
    }
}