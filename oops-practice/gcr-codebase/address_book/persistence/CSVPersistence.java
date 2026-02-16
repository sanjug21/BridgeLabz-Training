package persistence;

import java.io.*;
import java.util.*;
import model.Contact;

/**
 * UC 18: CSV-based persistence implementation
 * Handles reading/writing to CSV files with headers and proper escaping
 */
public class CSVPersistence implements IDataPersistence {
    
    @Override
    public void save(Map<String, List<Contact>> addressBook, String identifier) throws IOException {
        // Ensure .csv extension
        if (!identifier.toLowerCase().endsWith(".csv")) {
            identifier += ".csv";
        }
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(identifier));
        try {
            // Write CSV header
            writer.write("BookName,FirstName,LastName,PhoneNumber,Email,Address,City,State,Zip");
            writer.newLine();
            
            // Write contact data
            for (Map.Entry<String, List<Contact>> entry : addressBook.entrySet()) {
                String bookName = entry.getKey();
                List<Contact> contacts = entry.getValue();
                for (Contact contact : contacts) {
                    writer.write(escapeCSV(bookName) + "," + 
                                escapeCSV(contact.getFirstName()) + "," + 
                                escapeCSV(contact.getLastName()) + "," + 
                                escapeCSV(contact.getPhoneNumber()) + "," + 
                                escapeCSV(contact.getEmail()) + "," + 
                                escapeCSV(contact.getAddress()) + "," + 
                                escapeCSV(contact.getCity()) + "," + 
                                escapeCSV(contact.getState()) + "," + 
                                escapeCSV(contact.getZip()));
                    writer.newLine();
                }
            }
        } finally {
            writer.close();
        }
    }
    
    @Override
    public Map<String, List<Contact>> load(String identifier) throws IOException {
        // Ensure .csv extension
        if (!identifier.toLowerCase().endsWith(".csv")) {
            identifier += ".csv";
        }
        
        Map<String, List<Contact>> addressBook = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(identifier));
        try {
            String line;
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                // Skip header line
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                
                String[] parts = parseCSVLine(line);
                if (parts.length == 9) {
                    String bookName = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String phoneNumber = parts[3];
                    String email = parts[4];
                    String address = parts[5];
                    String city = parts[6];
                    String state = parts[7];
                    String zip = parts[8];
                    
                    Contact contact = new Contact(firstName, lastName, phoneNumber, email, address, city, state, zip);
                    
                    if (!addressBook.containsKey(bookName)) {
                        addressBook.put(bookName, new ArrayList<>());
                    }
                    addressBook.get(bookName).add(contact);
                }
            }
        } finally {
            reader.close();
        }
        return addressBook;
    }
    
    @Override
    public String getDataSourceName() {
        return "CSV";
    }
    
    // Helper method to escape CSV fields
    private String escapeCSV(String field) {
        if (field == null) {
            return "";
        }
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }
    
    // Helper method to parse CSV line
    private String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    field.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                result.add(field.toString());
                field = new StringBuilder();
            } else {
                field.append(c);
            }
        }
        result.add(field.toString());
        
        return result.toArray(new String[0]);
    }
}
