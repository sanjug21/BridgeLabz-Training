import java.util.Scanner;
import java.util.regex.Pattern;

public class HexColorValidator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Hex Color Code
        System.out.println("Enter a hex color code:");
        String hexColor = sc.nextLine();

        // 2. Validate Hex Color
        boolean isValid = validateHexColor(hexColor);

        // 3. Display Result
        if (isValid) {
            System.out.println(" \"" + hexColor + "\" → Valid");
        } else {
            System.out.println(" \"" + hexColor + "\" → Invalid");
        }

        sc.close();
    }

    public static boolean validateHexColor(String hexColor) {
        if (hexColor == null || hexColor.isEmpty()) {
            return false;
        }

        // Valid hex color: # followed by 6 hexadecimal characters
        String regex = "^#[0-9A-Fa-f]{6}$";
        return Pattern.matches(regex, hexColor);
    }
}
