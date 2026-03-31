package hospital_management_system;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private List<String> medicalHistory;

    public Patient(String id, String name, int age) {
        super(id, name, age);
        this.medicalHistory = new ArrayList<>();
    }

    public void addMedicalRecord(String record) {
        medicalHistory.add(record);
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    @Override
    public void displayDetails() {
        System.out.println("Patient [ID: " + id + ", Name: " + name + ", Age: " + age + "]");
    }
}