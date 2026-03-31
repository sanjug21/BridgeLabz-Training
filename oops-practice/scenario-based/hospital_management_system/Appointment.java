package hospital_management_system;

public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public String getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return "Appointment [ID: " + appointmentId + ", Patient: " + patient.getName() + 
               ", Doctor: " + doctor.getName() + ", Date: " + date + "]";
    }
}