package sensitive_data_tagging;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionService {

    public static String encryptIfSensitive(Object obj, String data) {
        if (obj instanceof SensitiveData) {
            return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
        }
        return data;
    }
}
