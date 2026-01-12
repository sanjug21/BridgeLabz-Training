package hospital_management_system;

import java.util.ArrayList;
import java.util.List;

public abstract class Doctor extends Person {
    protected String specialization;
    protected List<String> availableSlots;

    public Doctor(String id, String name, int age, String specialization) {
        super(id, name, age);
        this.specialization = specialization;
        this.availableSlots = new ArrayList<>();
        // Initialize some default slots
        availableSlots.add("10:00 AM");
        availableSlots.add("12:00 PM");
        availableSlots.add("02:00 PM");
        availableSlots.add("04:00 PM");
    }

    public String getSpecialization() { return specialization; }
    public List<String> getAvailableSlots() { return availableSlots; }

    public boolean bookSlot(String slot) {
        return availableSlots.remove(slot);
    }

    public void freeSlot(String slot) {
        availableSlots.add(slot);
    }

    public abstract double calculateConsultationFee();
}