import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;


public class EncryptDecryptCSV {
    
    private static final int ENCRYPTION_KEY = 7;

    static class Employee {
        String id;
        String name;
        String department;
        String email;
        String salary;

        public Employee(String id, String name, String department, String email, String salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.email = email;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{id='" + id + "', name='" + name + "', dept='" + department + 
                   "', email='" + email + "', salary='" + salary + "'}";
        }
    }

    public static void main(String[] args) {
        String encryptedFile = "java-8/gcr-codebase/csv_data_handeling/employees_encrypted.csv";
        String decryptedFile = "java-8/gcr-codebase/csv_data_handeling/employees_decrypted.csv";

        System.out.println("========== CSV ENCRYPTION AND DECRYPTION ==========");
        System.out.println();

        List<Employee> employees = createSampleEmployees();

        System.out.println("1. Writing encrypted CSV file...");
        writeEncryptedCSV(employees, encryptedFile);
        System.out.println();

        System.out.println("2. Reading and decrypting CSV file...");
        List<Employee> decryptedEmployees = readEncryptedCSV(encryptedFile);
        System.out.println();

        System.out.println("3. Writing decrypted data to new file...");
        writeDecryptedCSV(decryptedEmployees, decryptedFile);

        System.out.println("===================================================");
    }

    private static List<Employee> createSampleEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("101", "Amit Kumar", "IT", "amit.kumar@company.com", "75000"));
        employees.add(new Employee("102", "Kavya Reddy", "HR", "kavya.reddy@company.com", "65000"));
        employees.add(new Employee("103", "Rajesh Sharma", "Finance", "rajesh.sharma@company.com", "80000"));
        employees.add(new Employee("104", "Pooja Desai", "IT", "pooja.desai@company.com", "72000"));
        employees.add(new Employee("105", "Arun Nair", "Sales", "arun.nair@company.com", "68000"));
        return employees;
    }

    private static void writeEncryptedCSV(List<Employee> employees, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("ID,Name,Department,Email,Salary");

            for (Employee emp : employees) {
                String encryptedEmail = encrypt(emp.email);
                String encryptedSalary = encrypt(emp.salary);

                writer.println(emp.id + "," + emp.name + "," + emp.department + "," + 
                             encryptedEmail + "," + encryptedSalary);

                System.out.println("   Encrypted: " + emp.name);
                System.out.println("      Email: " + emp.email + " -> " + encryptedEmail);
                System.out.println("      Salary: " + emp.salary + " -> " + encryptedSalary);
            }

            System.out.println("   Encrypted file created: " + fileName);

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    private static List<Employee> readEncryptedCSV(String fileName) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length == 5) {
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    String department = fields[2].trim();
                    String encryptedEmail = fields[3].trim();
                    String encryptedSalary = fields[4].trim();

                    String decryptedEmail = decrypt(encryptedEmail);
                    String decryptedSalary = decrypt(encryptedSalary);

                    employees.add(new Employee(id, name, department, decryptedEmail, decryptedSalary));

                    System.out.println("   Decrypted: " + name);
                    System.out.println("      Email: " + encryptedEmail + " -> " + decryptedEmail);
                    System.out.println("      Salary: " + encryptedSalary + " -> " + decryptedSalary);
                }
            }

            System.out.println("   Total records decrypted: " + employees.size());

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + fileName + "' not found!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return employees;
    }

    private static void writeDecryptedCSV(List<Employee> employees, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("ID,Name,Department,Email,Salary");

            for (Employee emp : employees) {
                writer.println(emp.id + "," + emp.name + "," + emp.department + "," + 
                             emp.email + "," + emp.salary);
            }

            System.out.println("   Decrypted file created: " + fileName);
            System.out.println("   Total records written: " + employees.size());

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    private static String encrypt(String data) {
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    encrypted.append((char) ((c - 'A' + ENCRYPTION_KEY) % 26 + 'A'));
                } else {
                    encrypted.append((char) ((c - 'a' + ENCRYPTION_KEY) % 26 + 'a'));
                }
            } else if (Character.isDigit(c)) {
                encrypted.append((char) ((c - '0' + ENCRYPTION_KEY) % 10 + '0'));
            } else {
                encrypted.append(c);
            }
        }
        
        return Base64.getEncoder().encodeToString(encrypted.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static String decrypt(String encryptedData) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
            
            StringBuilder decrypted = new StringBuilder();
            
            for (int i = 0; i < decodedString.length(); i++) {
                char c = decodedString.charAt(i);
                if (Character.isLetter(c)) {
                    if (Character.isUpperCase(c)) {
                        decrypted.append((char) ((c - 'A' - ENCRYPTION_KEY + 26) % 26 + 'A'));
                    } else {
                        decrypted.append((char) ((c - 'a' - ENCRYPTION_KEY + 26) % 26 + 'a'));
                    }
                } else if (Character.isDigit(c)) {
                    decrypted.append((char) ((c - '0' - ENCRYPTION_KEY + 10) % 10 + '0'));
                } else {
                    decrypted.append(c);
                }
            }
            
            return decrypted.toString();
        } catch (IllegalArgumentException e) {
            System.err.println("Error decrypting data: " + e.getMessage());
            return encryptedData;
        }
    }
}
