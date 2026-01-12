package hospital_management_system;

import java.util.ArrayList;
import java.util.List;

public class HospitalManagementSystem implements HospitalService {
    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Added Patient: " + patient.getName());
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Added Doctor: " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
    }

    @Override
    public void bookAppointment(Patient patient, Doctor doctor, String date) throws AppointmentNotAvailableException {
        if (doctor.bookSlot(date)) {
            Appointment appointment = new Appointment("APT" + (appointments.size() + 1), patient, doctor, date);
            appointments.add(appointment);
            System.out.println("Appointment booked: " + patient.getName() + " with " + doctor.getName() + " on " + date);
            System.out.println("Consultation Fee: Rs. " + doctor.calculateConsultationFee());
        } else {
            throw new AppointmentNotAvailableException("Slot " + date + " is not available for Dr. " + doctor.getName());
        }
    }

    @Override
    public void cancelAppointment(Appointment appointment) {
        if (appointments.remove(appointment)) {
            appointment.getDoctor().freeSlot(appointment.getDate());
            System.out.println("Cancelled Appointment: " + appointment.getAppointmentId());
        } else {
            System.out.println("Appointment not found.");
        }
    }

    @Override
    public void viewMedicalHistory(Patient patient) {
        System.out.println("\nMedical History for " + patient.getName() + ":");
        List<String> history = patient.getMedicalHistory();
        if (history.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (String record : history) {
                System.out.println(" - " + record);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        HospitalManagementSystem hms = new HospitalManagementSystem();

        // 1. Add Doctors (Polymorphism in action)
        Doctor doc1 = new GeneralDoctor("D001", "Dr. RDJ", 45);
        Doctor doc2 = new SpecialistDoctor("D002", "Dr. Strange", 40, "Neurologist");
        
        hms.addDoctor(doc1);
        hms.addDoctor(doc2);

        // 2. Add Patients
        Patient p1 = new Patient("P001", "Manish", 30);
        Patient p2 = new Patient("P002", "Sagar", 50);
        
        hms.addPatient(p1);
        hms.addPatient(p2);

        // 3. Add Medical History
        p1.addMedicalRecord("Flu symptoms");
        p2.addMedicalRecord("Chronic Migraine");

        // 4. Book Appointments
        try {
            hms.bookAppointment(p1, doc1, "10:00 AM");
            hms.bookAppointment(p2, doc2, "12:00 PM");
            
            // 5. Exception Handling: Try booking same slot
            hms.bookAppointment(p2, doc1, "10:00 AM"); 
        } catch (AppointmentNotAvailableException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        // 6. View History
        hms.viewMedicalHistory(p1);
        hms.viewMedicalHistory(p2);
        
        // 7. Display Doctor Details (Polymorphism)
        System.out.println("--- Doctor Details ---");
        doc1.displayDetails();
        doc2.displayDetails();
    }
}