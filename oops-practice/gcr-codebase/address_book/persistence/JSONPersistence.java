package persistence;

import java.io.*;
import java.util.*;
import model.Contact;

/**
 * UC 18: JSON-based persistence implementation
 * Handles reading/writing to JSON files
 */
public class JSONPersistence implements IDataPersistence {
    
    @Override
    public void save(Map<String, List<Contact>> addressBook, String identifier) throws IOException {
        // Ensure .json extension
        if (!identifier.toLowerCase().endsWith(".json")) {
            identifier += ".json";
        }
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(identifier));
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
    
    @Override
    public Map<String, List<Contact>> load(String identifier) throws IOException {
        // Ensure .json extension
        if (!identifier.toLowerCase().endsWith(".json")) {
            identifier += ".json";
        }
        
        Map<String, List<Contact>> addressBook = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(identifier));
        try {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }
            
            parseJSON(jsonContent.toString(), addressBook);
        } finally {
            reader.close();
        }
        return addressBook;
    }
    
    @Override
    public String getDataSourceName() {
        return "JSON";
    }
    
    // Helper methods
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
    
    private void parseJSON(String json, Map<String, List<Contact>> addressBook) {
        String[] bookSections = json.split("\\{\"bookName\":");
        
        for (int i = 1; i < bookSections.length; i++) {
            String section = bookSections[i];
            
            String bookName = extractValue(section, "^\"", "\",");
            if (bookName.isEmpty()) continue;
            
            if (!addressBook.containsKey(bookName)) {
                addressBook.put(bookName, new ArrayList<>());
            }
            
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
