import java.io.*;
import java.util.*;


public class MergeCSVFiles {
    public static void main(String[] args) {
        String file1 = "java-8/gcr-codebase/csv_data_handeling/students1.csv";
        String file2 = "java-8/gcr-codebase/csv_data_handeling/students2.csv";
        String outputFile = "java-8/gcr-codebase/csv_data_handeling/students_merged.csv";
        String separator = ",";
        
        Map<String, String[]> file1Data = new HashMap<>();
        Map<String, String[]> file2Data = new HashMap<>();

        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {

            System.out.println("========== MERGING CSV FILES ==========");
            System.out.println("File 1: " + file1);
            System.out.println("File 2: " + file2);
            System.out.println("Output: " + outputFile);
            System.out.println("=======================================");

            String line;
            boolean isHeader = true;
            
            while ((line = reader1.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                String[] fields = line.split(separator);
                if (fields.length == 3) {
                    String id = fields[0].trim();
                    file1Data.put(id, new String[]{fields[1].trim(), fields[2].trim()});
                }
            }
            
            System.out.println("File 1 records loaded: " + file1Data.size());

            isHeader = true;
            while ((line = reader2.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                String[] fields = line.split(separator);
                if (fields.length == 3) {
                    String id = fields[0].trim();
                    file2Data.put(id, new String[]{fields[1].trim(), fields[2].trim()});
                }
            }
            
            System.out.println("File 2 records loaded: " + file2Data.size());
            System.out.println("=======================================");

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
                writer.println("ID,Name,Age,Marks,Grade");
                
                int mergedCount = 0;
                int unmatchedCount = 0;
                
                for (String id : file1Data.keySet()) {
                    String[] data1 = file1Data.get(id);
                    String[] data2 = file2Data.get(id);
                    
                    if (data2 != null) {
                        writer.println(id + "," + data1[0] + "," + data1[1] + "," + data2[0] + "," + data2[1]);
                        mergedCount++;
                        System.out.println("Merged ID " + id + ": " + data1[0] + " (Age: " + data1[1] + ", Marks: " + data2[0] + ", Grade: " + data2[1] + ")");
                    } else {
                        writer.println(id + "," + data1[0] + "," + data1[1] + ",,");
                        unmatchedCount++;
                        System.out.println("Partial ID " + id + ": " + data1[0] + " (No matching record in file 2)");
                    }
                }
                
                for (String id : file2Data.keySet()) {
                    if (!file1Data.containsKey(id)) {
                        String[] data2 = file2Data.get(id);
                        writer.println(id + ",,," + data2[0] + "," + data2[1]);
                        unmatchedCount++;
                        System.out.println("Partial ID " + id + ": No matching record in file 1 (Marks: " + data2[0] + ", Grade: " + data2[1] + ")");
                    }
                }
                
                System.out.println("=======================================");
                System.out.println("Merge completed successfully!");
                System.out.println("Total merged records: " + mergedCount);
                System.out.println("Partial records: " + unmatchedCount);
                System.out.println("Output file: " + outputFile);
                System.out.println("=======================================");
                
            } catch (IOException e) {
                System.err.println("Error writing to output file: " + e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }
}
