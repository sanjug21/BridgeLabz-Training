import java.util.Scanner;
import java.util.regex.Pattern;

public class LicensePlateValidator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input License Plate
        System.out.println("Enter a license plate number:");
        String licensePlate = sc.nextLine();

        // 2. Validate License Plate
        boolean isValid = validateLicensePlate(licensePlate);

        // 3. Display Result
        if (isValid) {
            System.out.println(" \"" + licensePlate + "\" → Valid");
        } else {
            System.out.println(" \"" + licensePlate + "\" → Invalid");
        }

        sc.close();
    }

    public static boolean validateLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            return false;
        }

        // License plate format: Two uppercase letters followed by four digits
        String regex = "^[A-Z]{2}\\d{4}$";
        return Pattern.matches(regex, licensePlate);
    }
}
