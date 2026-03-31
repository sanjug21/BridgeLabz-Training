public class LinearSearchWord {

    public static void main(String[] args) {
        String[] sentences = {
            "The sky is blue",
            "Hello world",
            "Java is powerful",
            "Coding is fun"
        };
        String targetWord = "Java";
        
        System.out.println("Looking for word: \"" + targetWord + "\"");
        String resultSentence = findSentenceWithWord(sentences, targetWord);
        System.out.println("Result: " + resultSentence);
    }

    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }
}
