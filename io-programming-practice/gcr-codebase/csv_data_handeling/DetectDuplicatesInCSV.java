import java.io.*;
import java.util.*;


public class DetectDuplicatesInCSV {
    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/students_duplicates.csv";
        String line;
        String separator = ",";
        
        Map<String, List<String>> idRecords = new HashMap<>();
        Set<String> duplicateIds = new HashSet<>();
        int totalRecords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            System.out.println("========== DUPLICATE DETECTION IN CSV ==========");
            System.out.println("File: " + csvFile);
            System.out.println("================================================");

            boolean isHeader = true;
            String header = "";
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    header = line;
                    isHeader = false;
                    continue;
                }

                totalRecords++;
                String[] fields = line.split(separator);
                
                if (fields.length >= 1) {
                    String id = fields[0].trim();
                    
                    if (idRecords.containsKey(id)) {
                        duplicateIds.add(id);
                    }
                    
                    if (!idRecords.containsKey(id)) {
                        idRecords.put(id, new ArrayList<>());
                    }
                    idRecords.get(id).add(line);
                }
            }

            System.out.println("Total records processed: " + totalRecords);
            System.out.println("Unique IDs: " + idRecords.size());
            System.out.println("Duplicate IDs found: " + duplicateIds.size());
            System.out.println("================================================\n");

            if (duplicateIds.isEmpty()) {
                System.out.println("No duplicate records found!");
            } else {
                System.out.println("========== DUPLICATE RECORDS ==========");
                System.out.println(header);
                System.out.println("=======================================");
                
                int duplicateCount = 0;
                for (String duplicateId : duplicateIds) {
                    List<String> records = idRecords.get(duplicateId);
                    System.out.println("\nDuplicate ID: " + duplicateId + " (Found " + records.size() + " times)");
                    
                    for (int i = 0; i < records.size(); i++) {
                        System.out.println("  [" + (i + 1) + "] " + records.get(i));
                        duplicateCount++;
                    }
                }
                
                System.out.println("\n=======================================");
                System.out.println("Total duplicate records: " + duplicateCount);
                System.out.println("=======================================");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
