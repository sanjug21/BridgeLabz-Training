import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine().trim();

            System.out.print("Enter Credit Card Number: ");
            String creditCard = sc.nextLine().trim();

            // Validate user details using custom exception
            HotelBooking.validateUserDetails(age, email, creditCard);

            // pick hotel and room details
            String hotelName = selectHotelName();
            String roomType = selectRoomType();

            // booking dates both input and validation
            System.out.print("Enter Check-in Date (yyyy-MM-dd): ");
            String checkInInput = sc.nextLine().trim();

            System.out.print("Enter Check-out Date (yyyy-MM-dd): ");
            String checkOutInput = sc.nextLine().trim();

            // breakfast option
            boolean breakfastIncluded = selectBreakfastOption();

            // Validate booking details using custom exception
            LocalDate checkInDate = parseDate(checkInInput);
            LocalDate checkOutDate = parseDate(checkOutInput);

            // Validate booking details using custom exception
            HotelBooking.validateBookingDetails(hotelName, roomType, checkInDate, checkOutDate);

            // calculate total by counting nights using epoch day difference
            int nights = (int) (checkOutDate.toEpochDay() - checkInDate.toEpochDay());
            double totalCost = HotelBooking.calculateBookingCost(roomType, nights, breakfastIncluded);

            // print booking details
            printBookingDetails(name, age, email, hotelName, roomType,
                    checkInDate, checkOutDate, nights, breakfastIncluded, totalCost);

        } catch (InvalidBookingDetailsException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static LocalDate parseDate(String input) throws InvalidBookingDetailsException {
        try {
            return LocalDate.parse(input, DATE_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new InvalidBookingDetailsException("Invalid date format");
        }
    }

    private static String selectHotelName() {
        System.out.println("Select Hotel: ");
        System.out.println("1. Hilton");
        System.out.println("2. Marriott");
        System.out.println("3. Hyatt");
        System.out.println("4. Sheraton");
        System.out.println("5. Radisson");
        System.out.print("Enter your choice: ");
        int hotelChoice = sc.nextInt();
        sc.nextLine();

        switch (hotelChoice) {
            case 1:
                return "Hilton";
            case 2:
                return "Marriott";
            case 3:
                return "Hyatt";
            case 4:
                return "Sheraton";
            case 5:
                return "Radisson";
            default:
                return null;
        }
    }

    private static String selectRoomType() {
        System.out.println("Select Room Type: ");
        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Suite");
        System.out.print("Enter your choice: ");
        int roomChoice = sc.nextInt();
        sc.nextLine();

        switch (roomChoice) {
            case 1:
                return "Standard";
            case 2:
                return "Deluxe";
            case 3:
                return "Suite";
            default:
                return null;
        }
    }

    private static boolean selectBreakfastOption() {
        System.out.println("Breakfast included: ");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Enter your choice: ");
        int breakfastChoice = sc.nextInt();
        sc.nextLine();

        switch (breakfastChoice) {
            case 1:
                return true;
            case 2:
                return false;
            default:
                return false;
        }
    }

    private static void printBookingDetails(String name, int age, String email,
            String hotelName, String roomType, LocalDate checkInDate, LocalDate checkOutDate,
            int nights, boolean breakfastIncluded, double totalCost) {
        System.out.println("\n\nBooking Successful!");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Hotel: " + hotelName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Check-in Date: " + checkInDate);
        System.out.println("Check-out Date: " + checkOutDate);
        System.out.println("Number of Nights: " + nights);
        System.out.println("Breakfast Included: " + breakfastIncluded);
        System.out.printf("Total Cost: $%.2f%n", totalCost);
    }
}
