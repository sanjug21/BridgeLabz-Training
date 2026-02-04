package string_length_checker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StringLengthChecker {

    public static void validateMessage(Message message, Function<String, Integer> lengthChecker, int limit) {
        int length = lengthChecker.apply(message.getContent());
        
        if (length > limit) {
            System.out.println("REJECTED - Length: " + length + "/" + limit + " | " + message);
        } else {
            System.out.println("ACCEPTED - Length: " + length + "/" + limit + " | " + message);
        }
    }

    public static void main(String[] args) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Alice", "Hello, how are you?"));
        messages.add(new Message("Bob", "I'm working on a new project that involves machine learning and AI."));
        messages.add(new Message("Charlie", "Thanks!"));
        messages.add(new Message("David", "This is a very long message that exceeds the normal character limit for SMS messages."));
        messages.add(new Message("Emma", "Meeting at 3 PM"));

        Function<String, Integer> getLength = str -> str.length();
        int smsLimit = 50;

        System.out.println("String Length Checker - SMS Limit: " + smsLimit + " characters");
        System.out.println("=========================================================");

        for (Message message : messages) {
            validateMessage(message, getLength, smsLimit);
        }

        System.out.println("\nTwitter Length Check (280 characters):");
        System.out.println("======================================");
        int twitterLimit = 280;
        Function<String, Integer> getLengthWithSpaces = String::length;

        for (Message message : messages) {
            int length = getLengthWithSpaces.apply(message.getContent());
            System.out.println(message.getSender() + " - " + length + "/" + twitterLimit + " characters");
        }
    }
}
