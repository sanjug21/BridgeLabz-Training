

public class NullPointerCheck {

    public static void main(String[] args) {
        // Call method to generate exception 
        // generateException();

        // Call method to handle exception
        handleException();
    }

    public static void generateException() {
        System.out.println("Generating NullPointerException...");
        String text=null;
        // This will throw NullPointerException
        int length=text.length();
        System.out.println("Length: " + length);
    }

    public static void handleException() {
        System.out.println("Handling NullPointerException...");
        String text=null;
        try {
            int length=text.length();
            System.out.println("Length: " + length);
        } catch (NullPointerException e) {
            System.out.println("Caught Exception: " + e);
        }
    }
}