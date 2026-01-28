import java.util.Scanner;
import java.util.regex.Pattern;

public class UsernameValidator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Username
        System.out.println("Enter a username to validate:");
        String username = sc.nextLine();

        // 2. Validate Username
        boolean isValid = validateUsername(username);

        // 3. Display Result
        if (isValid) {
            System.out.println("\"" + username + "\" → Valid");
        } else {
            System.out.println("\"" + username + "\" → Invalid");
        }

        sc.close();
    }

    public static boolean validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }

        // Valid username: starts with letter, only letters/numbers/underscore, 5-15 chars
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{4,14}$";
        return Pattern.matches(regex, username);
    }
}
