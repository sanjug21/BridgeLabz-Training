package access_static_fields;

public class Configuration {
    private static String API_KEY = "DEFAULT_API_KEY_12345";
    private static int MAX_CONNECTIONS = 100;
    private static double TIMEOUT_SECONDS = 30.0;
    private static boolean DEBUG_MODE = false;
    
    public static final String APP_NAME = "MyApplication";
    public static final String VERSION = "1.0.0";
    
    private Configuration() {
        throw new UnsupportedOperationException("Configuration class cannot be instantiated");
    }
    
    public static String getApiKey() {
        return API_KEY;
    }
    
    public static void setApiKey(String newKey) {
        if (newKey == null || newKey.isEmpty()) {
            throw new IllegalArgumentException("API_KEY cannot be null or empty");
        }
        API_KEY = newKey;
    }
    
    public static int getMaxConnections() {
        return MAX_CONNECTIONS;
    }
    
    public static double getTimeoutSeconds() {
        return TIMEOUT_SECONDS;
    }
    
    public static boolean isDebugMode() {
        return DEBUG_MODE;
    }
    
    public static void displayConfiguration() {
        System.out.println("Configuration Settings:");
        System.out.println("  API_KEY: " + API_KEY);
        System.out.println("  MAX_CONNECTIONS: " + MAX_CONNECTIONS);
        System.out.println("  TIMEOUT_SECONDS: " + TIMEOUT_SECONDS);
        System.out.println("  DEBUG_MODE: " + DEBUG_MODE);
        System.out.println("  APP_NAME: " + APP_NAME);
        System.out.println("  VERSION: " + VERSION);
    }
}
