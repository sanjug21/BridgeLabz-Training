package persistence;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import model.Contact;

/**
 * UC 18: Interface for data persistence operations
 * Following Open/Close Principle - open for extension, closed for modification
 * New data sources can be added by implementing this interface without modifying existing code
 */
public interface IDataPersistence {
    
    /**
     * Save address book data to the data source
     * @param addressBook The address book data to save
     * @param identifier The identifier (file name, database name, etc.)
     * @throws IOException If save operation fails
     */
    void save(Map<String, List<Contact>> addressBook, String identifier) throws IOException;
    
    /**
     * Load address book data from the data source
     * @param identifier The identifier (file name, database name, etc.)
     * @return Map of address book data
     * @throws IOException If load operation fails
     */
    Map<String, List<Contact>> load(String identifier) throws IOException;
    
    /**
     * Get the name of this persistence implementation
     * @return Name of the data source (e.g., "File", "CSV", "JSON", "Database")
     */
    String getDataSourceName();
}
