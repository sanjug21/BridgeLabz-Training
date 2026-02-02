import java.io.*;


public class ReadLargeCSVEfficiently {
    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/students.csv";
        String line;
        String separator = ",";
        int chunkSize = 100;
        int totalRecords = 0;
        int currentChunk = 0;
        int recordsInChunk = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile), 8192)) {
            System.out.println("========== READING LARGE CSV FILE EFFICIENTLY ==========");
            System.out.println("File: " + csvFile);
            System.out.println("Chunk Size: " + chunkSize + " records");
            System.out.println("========================================================");

            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    System.out.println("Header: " + line);
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(separator);
                
                if (fields.length >= 4) {
                    totalRecords++;
                    recordsInChunk++;
                    
                    if (recordsInChunk == chunkSize) {
                        currentChunk++;
                        System.out.println("Chunk " + currentChunk + " processed: " + recordsInChunk + " records | Total so far: " + totalRecords);
                        recordsInChunk = 0;
                    }
                }
            }

            if (recordsInChunk > 0) {
                currentChunk++;
                System.out.println("Chunk " + currentChunk + " processed: " + recordsInChunk + " records | Total so far: " + totalRecords);
            }

            System.out.println("========================================================");
            System.out.println("Processing completed!");
            System.out.println("Total chunks: " + currentChunk);
            System.out.println("Total records: " + totalRecords);
            System.out.println("========================================================");

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
