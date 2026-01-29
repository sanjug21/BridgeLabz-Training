import java.time.LocalDate;
import java.util.regex.Pattern;

public final class HotelBooking {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private HotelBooking() {
    }

    public static void validateUserDetails(int age, String email, String creditCard)
            throws InvalidBookingDetailsException {
        if (age < 18 || age > 100) {
            throw new InvalidBookingDetailsException("Invalid age");
        }

        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidBookingDetailsException("Invalid email");
        }

        if (creditCard == null || !creditCard.matches("\\d{16}")) {
            throw new InvalidBookingDetailsException("Invalid credit card number");
        }
    }

    public static void validateBookingDetails(String hotelName, String roomType,
            LocalDate checkInDate, LocalDate checkOutDate)
            throws InvalidBookingDetailsException {
        if (!isValidHotelName(hotelName)) {
            throw new InvalidBookingDetailsException("Invalid hotel name");
        }

        if (!isValidRoomType(roomType)) {
            throw new InvalidBookingDetailsException("Invalid room type");
        }

        LocalDate today = LocalDate.now();
        if (checkInDate == null || !checkInDate.isAfter(today)) {
            throw new InvalidBookingDetailsException("Invalid check-in date");
        }

        if (checkOutDate == null || !checkOutDate.isAfter(checkInDate)) {
            throw new InvalidBookingDetailsException("Invalid check-out date");
        }
    }

    public static double calculateBookingCost(String roomType, int numNights, boolean breakfastIncluded) {
        int ratePerNight;
        switch (roomType) {
            case "Standard":
                ratePerNight = 1000;
                break;
            case "Deluxe":
                ratePerNight = 2000;
                break;
            case "Suite":
                ratePerNight = 3000;
                break;
            default:
                ratePerNight = 0;
                break;
        }

        int breakfastRate = breakfastIncluded ? 100 : 0;
        return (ratePerNight + breakfastRate) * numNights;
    }

    private static boolean isValidHotelName(String hotelName) {
        if (hotelName == null) {
            return false;
        }

        switch (hotelName) {
            case "Hilton":
            case "Marriott":
            case "Hyatt":
            case "Sheraton":
            case "Radisson":
                return true;
            default:
                return false;
        }
    }

    private static boolean isValidRoomType(String roomType) {
        if (roomType == null) {
            return false;
        }

        switch (roomType) {
            case "Standard":
            case "Deluxe":
            case "Suite":
                return true;
            default:
                return false;
        }
    }
}
