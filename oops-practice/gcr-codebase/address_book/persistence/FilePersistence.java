package persistence;

import java.io.*;
import java.util.*;
import model.Contact;

/**
 * UC 18: File-based persistence implementation
 * Handles reading/writing to plain text files
 */
public class FilePersistence implements IDataPersistence {
    
    @Override
    public void save(Map<String, List<Contact>> addressBook, String identifier) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(identifier));
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
    
    @Override
    public Map<String, List<Contact>> load(String identifier) throws IOException {
        Map<String, List<Contact>> addressBook = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(identifier));
        try {
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
        return addressBook;
    }
    
    @Override
    public String getDataSourceName() {
        return "File";
    }
}
