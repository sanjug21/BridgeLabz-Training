import java.util.*;

public class distinctWord {
    
    public static void main(String[] args) {
        String input = "India is my country. India is a nation with 1.4 billion population";
        String[] words = input.split(" ");

        List<String> distinctWords = Arrays.asList(words).stream().distinct().toList();
        System.out.println(distinctWords);
    }
}
