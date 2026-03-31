package hospital_management_system;

public class SpecialistDoctor extends Doctor {
    public SpecialistDoctor(String id, String name, int age, String specialization) {
        super(id, name, age, specialization);
    }

    @Override
    public double calculateConsultationFee() {
        return 1000.0; // Specialist fee
    }

    @Override
    public void displayDetails() {
        System.out.println("Doctor (Specialist) [ID: " + id + ", Name: " + name + ", Spec: " + specialization + ", Fee: " + calculateConsultationFee() + "]");
    }
}