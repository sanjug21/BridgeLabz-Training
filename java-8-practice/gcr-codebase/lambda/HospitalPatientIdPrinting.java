
import java.util.*;

class Patient {
    private String patientId;
    private String name;
    private int age;
    private String department;

    public Patient(String patientId, String name, int age, String department) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Age: %d | Dept: %s", patientId, name, age, department);
    }
}

public class HospitalPatientIdPrinting {

    public static void main(String[] args) {
        List<Patient> patients = createPatientList();

        System.out.println("Patient IDs:");
        patients.stream()
                .map(Patient::getPatientId)
                .forEach(System.out::println);
    }

    private static List<Patient> createPatientList() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("P001", "John Doe", 45, "Cardiology"));
        patients.add(new Patient("P002", "Jane Smith", 32, "Orthopedics"));
        patients.add(new Patient("P003", "Bob Johnson", 67, "ICU"));
        patients.add(new Patient("P004", "Alice Brown", 28, "Pediatrics"));
        patients.add(new Patient("P005", "Charlie Davis", 55, "Cardiology"));
        return patients;
    }
}
