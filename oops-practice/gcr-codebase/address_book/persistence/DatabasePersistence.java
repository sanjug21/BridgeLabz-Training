package persistence;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import model.Contact;

/**
 * UC 18: Database-based persistence implementation
 * Handles reading/writing to SQLite database
 * Demonstrates Open/Close Principle - new data source added without modifying existing code
 */
public class DatabasePersistence implements IDataPersistence {
    
    private static final String DB_PREFIX = "jdbc:sqlite:";
    
    @Override
    public void save(Map<String, List<Contact>> addressBook, String identifier) throws IOException {
        // Ensure .db extension
        if (!identifier.toLowerCase().endsWith(".db")) {
            identifier += ".db";
        }
        
        String url = DB_PREFIX + identifier;
        
        try (Connection conn = DriverManager.getConnection(url)) {
            // Create tables if they don't exist
            createTables(conn);
            
            // Clear existing data
            clearData(conn);
            
            // Insert address book data
            for (Map.Entry<String, List<Contact>> entry : addressBook.entrySet()) {
                String bookName = entry.getKey();
                
                // Insert book
                int bookId = insertBook(conn, bookName);
                
                // Insert contacts
                List<Contact> contacts = entry.getValue();
                for (Contact contact : contacts) {
                    insertContact(conn, bookId, contact);
                }
            }
            
            System.out.println("[Database] Address Book saved to database successfully: " + identifier);
            
        } catch (SQLException e) {
            throw new IOException("Database error: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Map<String, List<Contact>> load(String identifier) throws IOException {
        // Ensure .db extension
        if (!identifier.toLowerCase().endsWith(".db")) {
            identifier += ".db";
        }
        
        String url = DB_PREFIX + identifier;
        Map<String, List<Contact>> addressBook = new HashMap<>();
        
        try (Connection conn = DriverManager.getConnection(url)) {
            // Check if tables exist
            if (!tablesExist(conn)) {
                System.out.println("[Database] No tables found. Returning empty address book.");
                return addressBook;
            }
            
            // Load all books
            String bookQuery = "SELECT id, name FROM AddressBooks";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(bookQuery)) {
                
                while (rs.next()) {
                    int bookId = rs.getInt("id");
                    String bookName = rs.getString("name");
                    
                    // Load contacts for this book
                    List<Contact> contacts = loadContactsForBook(conn, bookId);
                    addressBook.put(bookName, contacts);
                }
            }
            
            System.out.println("[Database] Address Book loaded from database successfully: " + identifier);
            
        } catch (SQLException e) {
            throw new IOException("Database error: " + e.getMessage(), e);
        }
        
        return addressBook;
    }
    
    @Override
    public String getDataSourceName() {
        return "Database";
    }
    
    // Helper methods
    private void createTables(Connection conn) throws SQLException {
        String createBooksTable = 
            "CREATE TABLE IF NOT EXISTS AddressBooks (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL UNIQUE)";
        
        String createContactsTable = 
            "CREATE TABLE IF NOT EXISTS Contacts (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "book_id INTEGER NOT NULL, " +
            "firstName TEXT NOT NULL, " +
            "lastName TEXT NOT NULL, " +
            "phoneNumber TEXT NOT NULL, " +
            "email TEXT NOT NULL, " +
            "address TEXT NOT NULL, " +
            "city TEXT NOT NULL, " +
            "state TEXT NOT NULL, " +
            "zip TEXT NOT NULL, " +
            "FOREIGN KEY (book_id) REFERENCES AddressBooks(id))";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createBooksTable);
            stmt.execute(createContactsTable);
        }
    }
    
    private boolean tablesExist(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "AddressBooks", null);
        return rs.next();
    }
    
    private void clearData(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM Contacts");
            stmt.execute("DELETE FROM AddressBooks");
        }
    }
    
    private int insertBook(Connection conn, String bookName) throws SQLException {
        String sql = "INSERT INTO AddressBooks (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, bookName);
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        throw new SQLException("Failed to insert book");
    }
    
    private void insertContact(Connection conn, int bookId, Contact contact) throws SQLException {
        String sql = "INSERT INTO Contacts (book_id, firstName, lastName, phoneNumber, email, address, city, state, zip) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setString(2, contact.getFirstName());
            pstmt.setString(3, contact.getLastName());
            pstmt.setString(4, contact.getPhoneNumber());
            pstmt.setString(5, contact.getEmail());
            pstmt.setString(6, contact.getAddress());
            pstmt.setString(7, contact.getCity());
            pstmt.setString(8, contact.getState());
            pstmt.setString(9, contact.getZip());
            pstmt.executeUpdate();
        }
    }
    
    private List<Contact> loadContactsForBook(Connection conn, int bookId) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM Contacts WHERE book_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Contact contact = new Contact(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("phoneNumber"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("zip")
                );
                contacts.add(contact);
            }
        }
        
        return contacts;
    }
}
