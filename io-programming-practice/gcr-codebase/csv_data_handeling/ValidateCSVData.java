import java.io.*;
import java.util.regex.*;

public class ValidateCSVData {
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    private static final String PHONE_REGEX = "^[0-9]{10}$";
    
    static class ValidationResult {
        int rowNumber;
        String id;
        String name;
        String email;
        String phone;
        boolean isValid;
        String errorMessage;

        ValidationResult(int rowNumber, String id, String name, String email, String phone) {
            this.rowNumber = rowNumber;
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.isValid = true;
            this.errorMessage = "";
        }
    }

    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/contacts.csv";
        String line;
        String separator = ",";
        int totalRows = 0;
        int validRows = 0;
        int invalidRows = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            System.out.println("========== CSV DATA VALIDATION REPORT ==========");
            System.out.println("File: " + csvFile);
            System.out.println("===============================================");

            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                totalRows++;
                String[] fields = line.split(separator);
                if (fields.length == 4) {
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    String email = fields[2].trim();
                    String phone = fields[3].trim();

                    ValidationResult result = validateRecord(totalRows, id, name, email, phone);

                    if (result.isValid) {
                        validRows++;
                        System.out.println("Row " + totalRows + ": VALID - " + name + 
                                         " (" + email + ", " + phone + ")");
                    } else {
                        invalidRows++;
                        System.out.println("   Row " + totalRows + ": INVALID");
                        System.out.println("   ID: " + id + ", Name: " + name);
                        System.out.println("   Email: " + email + ", Phone: " + phone);
                        System.out.println("   Error: " + result.errorMessage);
                        System.out.println();
                    }
                } else {
                    System.out.println(" Row " + totalRows + ": INVALID - Incorrect number of columns");
                    invalidRows++;
                }
            }

            System.out.println("===============================================");
            System.out.println("SUMMARY:");
            System.out.println("Total Records   : " + totalRows);
            System.out.println("Valid Records   : " + validRows);
            System.out.println("Invalid Records : " + invalidRows);
            System.out.println("Success Rate    : " + (totalRows > 0 ? 
                            String.format("%.2f%%", (validRows * 100.0) / totalRows) : "N/A"));
            System.out.println("===============================================");

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

  
    private static ValidationResult validateRecord(int rowNumber, String id, String name, 
                                                   String email, String phone) {
        ValidationResult result = new ValidationResult(rowNumber, id, name, email, phone);

        if (!isValidEmail(email)) {
            result.isValid = false;
            result.errorMessage = "Invalid email format: '" + email + "'";
            return result;
        }

        if (!isValidPhone(phone)) {
            result.isValid = false;
            if (!phone.matches("^[0-9]*$")) {
                result.errorMessage = "Phone number contains non-digit characters: '" + phone + "'";
            } else {
                result.errorMessage = "Phone number must contain exactly 10 digits. Found: " + phone.length();
            }
            return result;
        }

        return result;
    }

    private static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

  
    private static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
