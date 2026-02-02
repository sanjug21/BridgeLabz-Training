import java.io.*;
import java.util.*;


public class ConvertJSONToCSV {
    
    static class Student {
        String id;
        String name;
        int age;
        int marks;

        public Student(String id, String name, int age, int marks) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return "Student{id='" + id + "', name='" + name + "', age=" + age + ", marks=" + marks + "}";
        }
    }

    public static void main(String[] args) {
        String jsonFile = "java-8/gcr-codebase/csv_data_handeling/students.json";
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/students_from_json.csv";
        String jsonOutputFile = "java-8/gcr-codebase/csv_data_handeling/students_from_csv.json";

        System.out.println("========== JSON TO CSV AND CSV TO JSON CONVERTER ==========");
        System.out.println();

        System.out.println("1. Converting JSON to CSV...");
        convertJSONToCSV(jsonFile, csvFile);
        System.out.println();

        System.out.println("2. Converting CSV to JSON...");
        convertCSVToJSON(csvFile, jsonOutputFile);
        
        System.out.println("===========================================================");
    }

    private static void convertJSONToCSV(String jsonFile, String csvFile) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            String line;
            StringBuilder jsonContent = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }

            String json = jsonContent.toString();
            json = json.substring(json.indexOf('[') + 1, json.lastIndexOf(']'));
            
            String[] objects = json.split("\\},\\s*\\{");
            
            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "");
                
                String id = extractValue(obj, "id");
                String name = extractValue(obj, "name");
                int age = Integer.parseInt(extractValue(obj, "age"));
                int marks = Integer.parseInt(extractValue(obj, "marks"));
                
                students.add(new Student(id, name, age, marks));
            }

            System.out.println("   Parsed " + students.size() + " students from JSON");

            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println("ID,Name,Age,Marks");
                
                for (Student student : students) {
                    writer.println(student.id + "," + student.name + "," + student.age + "," + student.marks);
                    System.out.println("   Written: " + student);
                }
                
                System.out.println("   CSV file created: " + csvFile);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + jsonFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    private static void convertCSVToJSON(String csvFile, String jsonFile) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length == 4) {
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    int age = Integer.parseInt(fields[2].trim());
                    int marks = Integer.parseInt(fields[3].trim());
                    
                    students.add(new Student(id, name, age, marks));
                }
            }

            System.out.println("   Parsed " + students.size() + " students from CSV");

            try (PrintWriter writer = new PrintWriter(new FileWriter(jsonFile))) {
                writer.println("[");
                
                for (int i = 0; i < students.size(); i++) {
                    Student student = students.get(i);
                    writer.println("  {");
                    writer.println("    \"id\": \"" + student.id + "\",");
                    writer.println("    \"name\": \"" + student.name + "\",");
                    writer.println("    \"age\": " + student.age + ",");
                    writer.println("    \"marks\": " + student.marks);
                    
                    if (i < students.size() - 1) {
                        writer.println("  },");
                    } else {
                        writer.println("  }");
                    }
                    
                    System.out.println("   Written: " + student);
                }
                
                writer.println("]");
                System.out.println("   JSON file created: " + jsonFile);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    private static String extractValue(String json, String key) {
        String searchPattern = "\"" + key + "\":";
        int startIndex = json.indexOf(searchPattern);
        
        if (startIndex == -1) {
            return "";
        }
        
        startIndex += searchPattern.length();
        
        while (startIndex < json.length() && (json.charAt(startIndex) == ' ' || json.charAt(startIndex) == '\t')) {
            startIndex++;
        }
        
        int endIndex;
        
        if (startIndex < json.length() && json.charAt(startIndex) == '"') {
            startIndex++;
            endIndex = json.indexOf('"', startIndex);
            if (endIndex == -1) {
                endIndex = json.length();
            }
        } else {
            endIndex = startIndex;
            while (endIndex < json.length() && json.charAt(endIndex) != ',' && json.charAt(endIndex) != '}') {
                endIndex++;
            }
        }
        
        return json.substring(startIndex, endIndex).trim();
    }
}
