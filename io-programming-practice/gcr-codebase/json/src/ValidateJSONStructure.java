import org.json.JSONObject;
import java.util.regex.Pattern;

public class ValidateJSONStructure {
    
    // Email validation regex pattern
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    public static boolean isValidEmail(String email) {
        return pattern.matcher(email).matches();
    }
    
    public static boolean validateEmailField(JSONObject json) {
        try {
            if (!json.has("email")) {
                System.out.println("Error: Email field is missing");
                return false;
            }
            
            String email = json.getString("email");
            if (!isValidEmail(email)) {
                System.out.println("Error: Invalid email format: " + email);
                return false;
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("Error validating email: " + e.getMessage());
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("========== VALIDATE JSON STRUCTURE WITH EMAIL SCHEMA ==========\n");
        
        // Test Case 1: Valid JSON with valid email
        String json1 = "{\"name\": \"Vikram\", \"age\": 28, \"email\": \"vikram@example.com\"}";
        System.out.println("Test Case 1: Valid JSON with valid email");
        System.out.println("Input: " + json1);
        try {
            JSONObject jsonObject = new JSONObject(json1);
            if (validateEmailField(jsonObject)) {
                System.out.println("✓ Valid JSON Structure");
                System.out.println("  Name: " + jsonObject.getString("name"));
                System.out.println("  Age: " + jsonObject.getInt("age"));
                System.out.println("  Email: " + jsonObject.getString("email"));
            }
        } catch (Exception e) {
            System.out.println("✗ Invalid JSON: " + e.getMessage());
        }
        
        // Test Case 2: Valid JSON with invalid email
        String json2 = "{\"name\": \"Priya\", \"age\": 26, \"email\": \"invalid.email\"}";
        System.out.println("\nTest Case 2: Valid JSON with invalid email");
        System.out.println("Input: " + json2);
        try {
            JSONObject jsonObject = new JSONObject(json2);
            if (validateEmailField(jsonObject)) {
                System.out.println("✓ Valid email format");
            } else {
                System.out.println("✗ Email validation failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Invalid JSON: " + e.getMessage());
        }
        
        // Test Case 3: JSON missing email field
        String json3 = "{\"name\": \"Amit\", \"age\": 30}";
        System.out.println("\nTest Case 3: JSON missing email field");
        System.out.println("Input: " + json3);
        try {
            JSONObject jsonObject = new JSONObject(json3);
            if (validateEmailField(jsonObject)) {
                System.out.println("✓ Valid JSON Structure");
            } else {
                System.out.println("✗ JSON validation failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Invalid JSON: " + e.getMessage());
        }
        
        // Test Case 4: Another valid email
        String json4 = "{\"name\": \"Deepika\", \"age\": 25, \"email\": \"deepika.sharma@company.co.in\"}";
        System.out.println("\nTest Case 4: Another valid email format");
        System.out.println("Input: " + json4);
        try {
            JSONObject jsonObject = new JSONObject(json4);
            if (validateEmailField(jsonObject)) {
                System.out.println("✓ Valid JSON Structure");
                System.out.println("  Name: " + jsonObject.getString("name"));
                System.out.println("  Age: " + jsonObject.getInt("age"));
                System.out.println("  Email: " + jsonObject.getString("email"));
            }
        } catch (Exception e) {
            System.out.println("✗ Invalid JSON: " + e.getMessage());
        }
    }
}