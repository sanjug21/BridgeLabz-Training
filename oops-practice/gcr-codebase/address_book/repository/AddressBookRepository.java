package repository;

import java.util.*;
import java.io.*;
import model.Contact;

public class AddressBookRepository {
    private Map<String, List<Contact>> addressBook;
    public AddressBookRepository() {
        addressBook = new HashMap<>();
    }
    // getter  methods for addressBook
    public Map<String, List<Contact>> getAddressBook() {
        return addressBook;
    }
    
    // UC 6: Ability to create multiple unique address books
    public void addNewBook(String bookName) {
        addressBook.put(bookName, new ArrayList<>());
    }


    // UC 1: Ability to create a new address book contact
    // UC 2: Add contact to address book
    // UC 5: Ability to create multiple contact
    public void addContact(String bookName,Contact contact) {
        addressBook.get(bookName).add(contact);
    }

    // UC 3: Ability to edit contact from address book
    public void editContact(String bookName,Contact contact,Contact updatedContact){
                contact.setFirstName(updatedContact.getFirstName());
                contact.setLastName(updatedContact.getLastName());
                contact.setPhoneNumber(updatedContact.getPhoneNumber());
                contact.setEmail(updatedContact.getEmail());
                contact.setAddress(updatedContact.getAddress());
                contact.setCity(updatedContact.getCity());
                contact.setState(updatedContact.getState());
                contact.setZip(updatedContact.getZip());

    }

    // UC 4: Ability to delete contact from address book
    public void deleteContact(String bookName,Contact contact){
        addressBook.get(bookName).remove(contact);
    }

    // UC 13: Ability to write address book to file
    public void writeToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        try {
            for (Map.Entry<String, List<Contact>> entry : addressBook.entrySet()) {
                String bookName = entry.getKey();
                List<Contact> contacts = entry.getValue();
                for (Contact contact : contacts) {
                    writer.write(bookName + "," + 
                                contact.getFirstName() + "," + 
                                contact.getLastName() + "," + 
                                contact.getPhoneNumber() + "," + 
                                contact.getEmail() + "," + 
                                contact.getAddress() + "," + 
                                contact.getCity() + "," + 
                                contact.getState() + "," + 
                                contact.getZip());
                    writer.newLine();
                }
            }
        } finally {
            writer.close();
        }
    }

    // UC 13: Ability to read address book from file
    public void readFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        try {
            addressBook.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
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
    }

    // UC 14: Ability to write address book to CSV file with headers
    public void writeToCSV(String fileName) throws IOException {
        // Ensure .csv extension
        if (!fileName.toLowerCase().endsWith(".csv")) {
            fileName += ".csv";
        }
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
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

    // UC 14: Ability to read address book from CSV file with headers
    public void readFromCSV(String fileName) throws IOException {
        // Ensure .csv extension
        if (!fileName.toLowerCase().endsWith(".csv")) {
            fileName += ".csv";
        }
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        try {
            addressBook.clear();
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
    }

    // Helper method to escape CSV fields (handle commas and quotes)
    private String escapeCSV(String field) {
        if (field == null) {
            return "";
        }
        // If field contains comma, quote, or newline, wrap in quotes and escape quotes
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }

    // Helper method to parse CSV line (handle quoted fields)
    private String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                // Check for escaped quote
                if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    field.append('"');
                    i++; // Skip next quote
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

    // UC 15: Ability to write address book to JSON file
    public void writeToJSON(String fileName) throws IOException {
        // Ensure .json extension
        if (!fileName.toLowerCase().endsWith(".json")) {
            fileName += ".json";
        }
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        try {
            writer.write("{");
            writer.newLine();
            writer.write("  \"addressBooks\": [");
            writer.newLine();
            
            boolean firstBook = true;
            for (Map.Entry<String, List<Contact>> entry : addressBook.entrySet()) {
                if (!firstBook) {
                    writer.write(",");
                    writer.newLine();
                }
                firstBook = false;
                
                String bookName = entry.getKey();
                List<Contact> contacts = entry.getValue();
                
                writer.write("    {");
                writer.newLine();
                writer.write("      \"bookName\": \"" + escapeJSON(bookName) + "\",");
                writer.newLine();
                writer.write("      \"contacts\": [");
                writer.newLine();
                
                boolean firstContact = true;
                for (Contact contact : contacts) {
                    if (!firstContact) {
                        writer.write(",");
                        writer.newLine();
                    }
                    firstContact = false;
                    
                    writer.write("        {");
                    writer.newLine();
                    writer.write("          \"firstName\": \"" + escapeJSON(contact.getFirstName()) + "\",");
                    writer.newLine();
                    writer.write("          \"lastName\": \"" + escapeJSON(contact.getLastName()) + "\",");
                    writer.newLine();
                    writer.write("          \"phoneNumber\": \"" + escapeJSON(contact.getPhoneNumber()) + "\",");
                    writer.newLine();
                    writer.write("          \"email\": \"" + escapeJSON(contact.getEmail()) + "\",");
                    writer.newLine();
                    writer.write("          \"address\": \"" + escapeJSON(contact.getAddress()) + "\",");
                    writer.newLine();
                    writer.write("          \"city\": \"" + escapeJSON(contact.getCity()) + "\",");
                    writer.newLine();
                    writer.write("          \"state\": \"" + escapeJSON(contact.getState()) + "\",");
                    writer.newLine();
                    writer.write("          \"zip\": \"" + escapeJSON(contact.getZip()) + "\"");
                    writer.newLine();
                    writer.write("        }");
                }
                
                writer.newLine();
                writer.write("      ]");
                writer.newLine();
                writer.write("    }");
            }
            
            writer.newLine();
            writer.write("  ]");
            writer.newLine();
            writer.write("}");
        } finally {
            writer.close();
        }
    }

    // UC 15: Ability to read address book from JSON file
    public void readFromJSON(String fileName) throws IOException {
        // Ensure .json extension
        if (!fileName.toLowerCase().endsWith(".json")) {
            fileName += ".json";
        }
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        try {
            addressBook.clear();
            StringBuilder jsonContent = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }
            
            parseJSON(jsonContent.toString());
        } finally {
            reader.close();
        }
    }

    // Helper method to escape JSON strings
    private String escapeJSON(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }

    // Helper method to parse JSON content
    private void parseJSON(String json) {
        // Simple JSON parser for our specific structure
        String[] bookSections = json.split("\\{\"bookName\":");
        
        for (int i = 1; i < bookSections.length; i++) {
            String section = bookSections[i];
            
            // Extract book name
            String bookName = extractValue(section, "^\"", "\",");
            if (bookName.isEmpty()) continue;
            
            if (!addressBook.containsKey(bookName)) {
                addressBook.put(bookName, new ArrayList<>());
            }
            
            // Extract contacts
            String contactsSection = section.substring(section.indexOf("\"contacts\":"));
            String[] contactParts = contactsSection.split("\\{\"firstName\":");
            
            for (int j = 1; j < contactParts.length; j++) {
                String contactStr = contactParts[j];
                
                String firstName = extractValue(contactStr, "^\"", "\",");
                String lastName = extractValue(contactStr, "\"lastName\":\"", "\",");
                String phoneNumber = extractValue(contactStr, "\"phoneNumber\":\"", "\",");
                String email = extractValue(contactStr, "\"email\":\"", "\",");
                String address = extractValue(contactStr, "\"address\":\"", "\",");
                String city = extractValue(contactStr, "\"city\":\"", "\",");
                String state = extractValue(contactStr, "\"state\":\"", "\",");
                String zip = extractValue(contactStr, "\"zip\":\"", "\"");
                
                Contact contact = new Contact(
                    unescapeJSON(firstName),
                    unescapeJSON(lastName),
                    unescapeJSON(phoneNumber),
                    unescapeJSON(email),
                    unescapeJSON(address),
                    unescapeJSON(city),
                    unescapeJSON(state),
                    unescapeJSON(zip)
                );
                
                addressBook.get(bookName).add(contact);
            }
        }
    }

    // Helper method to extract JSON value
    private String extractValue(String json, String startPattern, String endPattern) {
        int startIndex = json.indexOf(startPattern);
        if (startIndex == -1) return "";
        
        startIndex += startPattern.length();
        int endIndex = json.indexOf(endPattern, startIndex);
        
        if (endIndex == -1) {
            endIndex = json.length();
        }
        
        return json.substring(startIndex, endIndex);
    }

    // Helper method to unescape JSON strings
    private String unescapeJSON(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\\"", "\"")
                    .replace("\\n", "\n")
                    .replace("\\r", "\r")
                    .replace("\\t", "\t")
                    .replace("\\\\", "\\");
    }

}
