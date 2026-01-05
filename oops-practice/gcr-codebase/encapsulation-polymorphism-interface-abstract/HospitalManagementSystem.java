import java.util.ArrayList;
import java.util.List;

// Interface for managing medical records
interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

// Abstract class for Patient
abstract class PatientRecord {
    private String patientId;
    private String name;
    private int age;

    public PatientRecord(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }
    // getter  methods for patientId, name, and age
    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    // declared abstract methods
    public abstract double calculateBill();

    public void getPatientDetails() {
        System.out.println("ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class InPatient extends PatientRecord implements MedicalRecord {
    private double dailyCharge;
    private int daysAdmitted;
    private List<String> records = new ArrayList<>(); // Encapsulated sensitive data

    public InPatient(String id, String name, int age, double dailyCharge, int days) {
        super(id, name, age);
        this.dailyCharge = dailyCharge;
        this.daysAdmitted = days;
    }
    // abstract method implementation
    @Override
    public double calculateBill() {
        return dailyCharge * daysAdmitted;
    }
    // interface methods
    @Override
    public void addRecord(String record) {
        records.add(record);
    }
    // interface methods
    @Override
    public void viewRecords() {
        System.out.println("Medical Records for " + getName() + ": " + records);
    }
}

class OutPatient extends PatientRecord implements MedicalRecord {
    private double consultationFee;
    private List<String> records = new ArrayList<>(); // Encapsulated sensitive data

    public OutPatient(String id, String name, int age, double fee) {
        super(id, name, age);
        this.consultationFee = fee;
    }
    // abstract method implementation
    @Override
    public double calculateBill() {
        return consultationFee;
    }
    // interface methods
    @Override
    public void addRecord(String record) {
        records.add(record);
    }
    // interface methods
    @Override
    public void viewRecords() {
        System.out.println("Medical Records for " + getName() + ": " + records);
    }
   
}

public class HospitalManagementSystem {
    public static void main(String[] args) {
        List<PatientRecord> patients = new ArrayList<>();
        
        InPatient p1 = new InPatient("P001", "Dharmesh", 45, 2000, 5);
        p1.addRecord("Diagnosed with Typhoid");
        p1.addRecord("Prescribed Antibiotics");
        
        OutPatient p2 = new OutPatient("P002", "Pappu", 30, 500);
        p2.addRecord("Routine Checkup");
        p2.addRecord("BP Normal");

        patients.add(p1);
        patients.add(p2);

        System.out.println("=== Hospital Patient Management ===");
        for (PatientRecord p : patients) {
            p.getPatientDetails();
            System.out.println("Total Bill: Rs " + p.calculateBill());
            
            if (p instanceof MedicalRecord) {
                ((MedicalRecord) p).viewRecords();
            }
            System.out.println("---------------------------------");
        }
    }
}