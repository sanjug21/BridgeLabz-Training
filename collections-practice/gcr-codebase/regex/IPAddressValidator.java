import java.util.Scanner;
import java.util.regex.Pattern;
public class IPAddressValidator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input IP Address
        System.out.println("Enter an IP address:");
        String ipAddress = sc.nextLine();

        // 2. Validate IP Address
        boolean isValid = validateIPAddress(ipAddress);

        // 3. Display Result
        if (isValid) {
            System.out.println(" \"" + ipAddress + "\" is a valid IP address");
        } else {
            System.out.println(" \"" + ipAddress + "\" is not a valid IP address");
        }

        sc.close();
    }

    public static boolean validateIPAddress(String ipAddress) {
        if (ipAddress == null || ipAddress.isEmpty()) {
            return false;
        }

        // IP address pattern
        String regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return Pattern.matches(regex, ipAddress);
    }
}
