package hospital_management_system;

public interface HospitalService {
    void addPatient(Patient patient);
    void addDoctor(Doctor doctor);
    void bookAppointment(Patient patient, Doctor doctor, String date) throws AppointmentNotAvailableException;
    void cancelAppointment(Appointment appointment);
    void viewMedicalHistory(Patient patient);
}