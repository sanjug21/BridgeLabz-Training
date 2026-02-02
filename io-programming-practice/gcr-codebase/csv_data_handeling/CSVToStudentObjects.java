import java.io.*;
import java.util.*;

public class CSVToStudentObjects {

    static class Student {
        private String id;
        private String name;
        private int age;
        private int marks;

       
        public Student(String id, String name, int age, int marks) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.marks = marks;
        }

        // Getters
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public int getMarks() {
            return marks;
        }

        // Setters
        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setMarks(int marks) {
            this.marks = marks;
        }

        @Override
        public String toString() {
            return String.format("Student{id='%s', name='%-20s', age=%d, marks=%d}", 
                               id, name, age, marks);
        }

       
        public String toFormattedString() {
            return String.format("%-5s %-20s %-5d %-10d", id, name, age, marks);
        }
    }

    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/students.csv";
        String line;
        String separator = ",";
        List<Student> students = new ArrayList<>();

        System.out.println("========== CONVERTING CSV TO JAVA OBJECTS ==========");
        System.out.println("Reading file: " + csvFile);
        System.out.println("====================================================");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            boolean isHeader = true;
            int rowNumber = 0;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                rowNumber++;
                String[] fields = line.split(separator);
                
                if (fields.length == 4) {
                    try {
                        String id = fields[0].trim();
                        String name = fields[1].trim();
                        int age = Integer.parseInt(fields[2].trim());
                        int marks = Integer.parseInt(fields[3].trim());

                        Student student = new Student(id, name, age, marks);
                        students.add(student);
                        
                        System.out.println("Row " + rowNumber + " converted: " + student);

                    } catch (NumberFormatException e) {
                        System.err.println(" Row " + rowNumber + " - Error parsing numeric values: " + e.getMessage());
                    }
                } else {
                    System.err.println(" Row " + rowNumber + " - Invalid number of columns");
                }
            }

            System.out.println("====================================================");
            System.out.println("Conversion completed. Total students loaded: " + students.size());
            System.out.println("====================================================\n");

            // Display all students in a formatted table
            displayStudentList(students);

            

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void displayStudentList(List<Student> students) {
        System.out.println("========== STUDENT LIST (FORMATTED) ==========");
        System.out.println(String.format("%-5s %-20s %-5s %-10s", "ID", "Name", "Age", "Marks"));
        System.out.println("==============================================");
        
        for (Student student : students) {
            System.out.println(student.toFormattedString());
        }
        
        System.out.println("==============================================\n");
    }

   
}
