
public class Patient {
    public String name;
    public int age;
    public String ailment;
    public final int patientID;
    public static String hospitalName = "City Hospital";
    private static int totalPatients=0;

    public Patient(String name,int age,String ailment,int patientID){
        this.name = name;
        this.age = age;
        this.ailment = ailment;
        this.patientID = patientID;
        totalPatients++;
    }

    // Static method to get total number of patients
    public static int getTotalPatients(){
        return totalPatients; 
    }

    //dispalt method to display patient details
    public void displayDetails(){
        System.out.println("Patient ID: " + patientID);
        System.out.println("Patient name: " + name);
        System.out.println("Patient age: " + age);
        System.out.println("Patient ailment: " + ailment);
    }
    public static void main(String[] args) {
        Patient p1 = new Patient("Sagar", 30, "Flu", 101);
        Patient p2 = new Patient("Manish", 25, "Cold", 102);

        // Using instanceof to check object type
        System.out.println("p1 instanceof Patient: " + (p1 instanceof Patient));
        System.out.println("p2 instanceof Patient: " + (p2 instanceof Patient));

        // Display total patients and hospital name
        System.out.println("Total Patients: " + Patient.getTotalPatients());
        System.out.println("Hospital Name: " + Patient.hospitalName);

        
        // Display patient details
        p1.displayDetails();
        p2.displayDetails();
    }
}
