import java.util.*;

// Abstraction
interface IPayable {
    double calculateBill();
}

// Encapsulation & Inheritance
abstract class Patient {
    private String name;
    private int age;
    protected String diagnosis;

    public Patient(String name, int age, String diagnosis) {
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    public String getName() { return name; }
    
    // Polymorphism
    public void displayInfo() {
        System.out.println("Patient: " + name + ", Age: " + age + ", Diagnosis: " + diagnosis);
    }
}

class InPatient extends Patient implements IPayable {
    private double roomCharges;
    private int daysAdmitted;

    public InPatient(String name, int age, String diagnosis, double roomCharges, int daysAdmitted) {
        super(name, age, diagnosis);
        this.roomCharges = roomCharges;
        this.daysAdmitted = daysAdmitted;
    }

    @Override
    public double calculateBill() {
        return roomCharges * daysAdmitted;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: In-Patient | Days Admitted: " + daysAdmitted);
    }
}

class OutPatient extends Patient implements IPayable {
    private double consultationFee;

    public OutPatient(String name, int age, String diagnosis, double consultationFee) {
        super(name, age, diagnosis);
        this.consultationFee = consultationFee;
    }

    @Override
    public double calculateBill() {
        return consultationFee;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Out-Patient");
    }
}



public class HospitalManagementSystem {
    static List<Patient> patients = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Hospital Management System ===");
            System.out.println("1. Register In-Patient");
            System.out.println("2. Register Out-Patient");
            System.out.println("3. Display All Patients");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String inName = sc.nextLine();
                    System.out.print("Age: ");
                    int inAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Diagnosis: ");
                    String inDiag = sc.nextLine();
                    System.out.print("Room Charge per day: ");
                    double rate = sc.nextDouble();
                    System.out.print("Days Admitted: ");
                    int days = sc.nextInt();
                    patients.add(new InPatient(inName, inAge, inDiag, rate, days));
                    System.out.println("Patient Registered.");
                    break;
                case 2:
                    System.out.print("Name: ");
                    String outName = sc.nextLine();
                    System.out.print("Age: ");
                    int outAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Diagnosis: ");
                    String outDiag = sc.nextLine();
                    System.out.print("Consultation Fee: ");
                    double fee = sc.nextDouble();
                    patients.add(new OutPatient(outName, outAge, outDiag, fee));
                    System.out.println("Patient Registered.");
                    break;
                case 3:
                    if(patients.isEmpty()) System.out.println("No patients registered.");
                    else System.out.println("--- Patients ---");
                    for (Patient p : patients) {
                        p.displayInfo();
                    }
                    break;
                case 4:
                    System.out.print("Enter Patient Name to bill: ");
                    String billName = sc.nextLine();
                    boolean found = false;
                    for (Patient p : patients) {                        
                        if (p.getName().equalsIgnoreCase(billName) && p instanceof IPayable) {
                            System.out.println("Total Bill for " + p.getName() + ": $" + ((IPayable) p).calculateBill());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Patient not found or cannot be billed.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting Hospital Management System. Goodbye!");
                    System.exit(0);
                    break;
                }
            }
            
    }
}