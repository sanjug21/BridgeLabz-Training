package sensitive_data_tagging;

public class SensitiveDataTagging {

    public static void main(String[] args) {
        UserCredentials creds = new UserCredentials("psingh", "Pass@2026");
        String apiKey = "public-api-key-001";

        System.out.println("Sensitive Data Tagging");
        System.out.println("=======================");

        String encryptedPassword = EncryptionService.encryptIfSensitive(creds, creds.getPassword());
        String rawApiKey = EncryptionService.encryptIfSensitive(apiKey, apiKey);

        System.out.println(creds + " | Encrypted Password: " + encryptedPassword);
        System.out.println("API Key (not sensitive): " + rawApiKey);
    }
}
