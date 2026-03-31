import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Doctor {
    String name;
    String specialty;
    boolean availableOnWeekends;

    public Doctor(String name, String specialty, boolean availableOnWeekends) {
        this.name = name;
        this.specialty = specialty;
        this.availableOnWeekends = availableOnWeekends;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public boolean isAvailableOnWeekends() {
        return availableOnWeekends;
    }
}

public class HospitalDoctorAvailability {

    public static void main(String[] args) {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Smith", "Cardiology", true));
        doctors.add(new Doctor("Dr. Johnson", "Neurology", false));
        doctors.add(new Doctor("Dr. Williams", "Orthopedics", true));
        doctors.add(new Doctor("Dr. Brown", "Cardiology", false));
        doctors.add(new Doctor("Dr. Davis", "Pediatrics", true));
        doctors.add(new Doctor("Dr. Miller", "Neurology", true));
        doctors.add(new Doctor("Dr. Wilson", "Orthopedics", false));
        doctors.add(new Doctor("Dr. Moore", "Pediatrics", false));

        System.out.println("Doctors Available on Weekends:");
        doctors.stream()
                .filter(d -> d.isAvailableOnWeekends())
                .sorted(Comparator.comparing(Doctor::getSpecialty))
                .forEach(d -> System.out.println(d.getName() + " - " + d.getSpecialty()));
    }
}
