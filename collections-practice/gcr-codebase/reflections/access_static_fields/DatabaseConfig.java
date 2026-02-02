package access_static_fields;

public class DatabaseConfig {
    private static String DB_HOST = "localhost";
    private static int DB_PORT = 5432;
    private static String DB_NAME = "mydb";
    private static String DB_USER = "admin";
    private static String DB_PASSWORD = "secret123";
    
    public static void showConfig() {
        System.out.println("Database Configuration:");
        System.out.println("  Host: " + DB_HOST);
        System.out.println("  Port: " + DB_PORT);
        System.out.println("  Database: " + DB_NAME);
        System.out.println("  User: " + DB_USER);
        System.out.println("  Password: " + "*".repeat(DB_PASSWORD.length()));
    }
}
