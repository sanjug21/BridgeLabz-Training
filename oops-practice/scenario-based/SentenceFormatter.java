import java.util.Scanner;
public class SentenceFormatter {
    static Scanner sc;

    public static void main(String[] args) {
        sc=new Scanner(System.in);
        System.out.println("Enter scenario number (1 or 2): ");
        int scenario=sc.nextInt();
        sc.nextLine(); // consume newline
        
        if(scenario==1){
            scenario1();
        }
        else if(scenario==2){
            Scenario2();   
        }
        else{
            System.out.println("Invalid scenario number.");
        }


        sc.close();
    }

   

    
    public static void scenario1() {

        System.out.println("Scenario 1: ");
        System.out.println("Enter a paragraph to format: ");
        // Trim extra spaces and replace multiple spaces with a single space
        String paragraph=sc.nextLine().trim();
        if(paragraph.length()==0 || paragraph==null){
            return;
        }
        String formatted=formatParagraph(paragraph);
        System.out.println("Formatted Paragraph: ");
        System.out.println(formatted);
    }
    
    public static String formatParagraph(String paragraph) {
        StringBuilder formatted = new StringBuilder();
        boolean capitalizeNext = true;

        for (int i = 0; i < paragraph.length(); i++) {
            char currentChar = paragraph.charAt(i);
            // Check if the character is a letter
            if (Character.isLetter(currentChar)) {
                // Capitalize the first letter of a sentence
                if (capitalizeNext) {
                    formatted.append(Character.toUpperCase(currentChar));
                    capitalizeNext = false;
                } else {
                    formatted.append(currentChar);
                }
            } else {
                // Handle multiple spaces
                if (currentChar == ' ') {
                    while (i + 1 < paragraph.length() && paragraph.charAt(i + 1) == ' ') {
                        i++;
                    }
                    // Only append space if next char is not punctuation
                    if (i + 1 < paragraph.length()) {
                        char nextChar = paragraph.charAt(i + 1);
                        if (nextChar != '.' && nextChar != '?' && nextChar != '!') {
                            formatted.append(' ');
                        }
                    }
                }
                // Handle punctuation
                else if (currentChar == '.' || currentChar == '?' || currentChar == '!') {
                    formatted.append(currentChar);
                    capitalizeNext = true;
                } else {
                    formatted.append(currentChar); // keep commas, quotes, etc.
                }
            }
        }

        return formatted.toString().trim();
    }

    public static void Scenario2() {
        System.out.println("Scenario 2: ");
        System.out.println("Enter a paragraph to analyze: ");
        String paragraph = sc.nextLine().trim();
        System.out.println("Enter the word to find: ");
        String wordToFind = sc.next().trim();
        System.out.println("Enter the word to replace: ");
        String wordToReplace=sc.next().trim();
        // Split paragraph into words using regex to handle multiple spaces using \\s+
        String words[] = paragraph.split("\\s+");

        String longestWord="";
        int longestWordLength=0;
        // Find the longest word and its length
        for(String word:words){
            if(word.length()>longestWordLength){
                longestWordLength=word.length();
                longestWord=word;
            }
        }
        System.out.println("Longest Word: "+longestWord);
        System.out.println("Length of Longest Word: "+longestWordLength);

        // Replace occurrences of wordToFind with wordToReplace using word boundaries (\\b)
        // String modifiedParagraph = paragraph.replaceAll("\\b" + wordToFind + "\\b", wordToReplace);


        // or we can do it manually as below
        StringBuilder modifiedParagraph = new StringBuilder();
        for(String word:words){
            if(word.equals(wordToFind)){
                modifiedParagraph.append(wordToReplace).append(" ");
            }
            else{
                modifiedParagraph.append(word).append(" ");
            }
        }
        System.out.println("Modified Paragraph: ");
        System.out.println(modifiedParagraph.toString().trim());

    }
}
