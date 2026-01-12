package hospital_management_system;

public class GeneralDoctor extends Doctor {
    public GeneralDoctor(String id, String name, int age) {
        super(id, name, age, "General Physician");
    }

    @Override
    public double calculateConsultationFee() {
        return 500.0; // Standard fee
    }

    @Override
    public void displayDetails() {
        System.out.println("Doctor (General) [ID: " + id + ", Name: " + name + ", Fee: " + calculateConsultationFee() + "]");
    }
}