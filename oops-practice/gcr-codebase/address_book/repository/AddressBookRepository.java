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

}
