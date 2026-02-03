
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

enum AlertType {
    CRITICAL, URGENT, ROUTINE, MEDICATION, VITAL_SIGNS, EMERGENCY, INFO
}

enum Priority {
    HIGH, MEDIUM, LOW
}

class PatientAlert {
    private String patientId;
    private String patientName;
    private AlertType type;
    private Priority priority;
    private String message;
    private String department;
    private boolean acknowledged;

    public PatientAlert(String patientId, String patientName, AlertType type,
            Priority priority, String message, String department) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.type = type;
        this.priority = priority;
        this.message = message;
        this.department = department;
        this.acknowledged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public AlertType getType() {
        return type;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getMessage() {
        return message;
    }

    public String getDepartment() {
        return department;
    }

    public boolean isAcknowledged() {
        return acknowledged;
    }

    @Override
    public String toString() {
        String ack = acknowledged ? "✓" : "✗";
        return String.format("[%s] %s | Patient: %s | Dept: %s | %s | Ack: %s",
                priority, type, patientName, department, message, ack);
    }
}

public class NotificationFiltering {

    public static void main(String[] args) {
        List<PatientAlert> alerts = createSampleAlerts();

        System.out.println("Filtered Alerts:");
        Predicate<PatientAlert> preference = alert -> alert.getPriority() == Priority.HIGH
                || alert.getDepartment().equals("ICU");
        filterAndDisplay(alerts, preference);
    }

    private static void filterAndDisplay(List<PatientAlert> alerts, Predicate<PatientAlert> filter) {
        List<PatientAlert> filtered = alerts.stream()
                .filter(filter)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No alerts match this filter.");
        } else {
            System.out.println("Found " + filtered.size() + " alert(s):");
            filtered.forEach(alert -> System.out.println(alert));
        }
    }

    private static List<PatientAlert> createSampleAlerts() {
        List<PatientAlert> alerts = new ArrayList<>();

        alerts.add(new PatientAlert("P001", "John Doe", AlertType.CRITICAL, Priority.HIGH,
                "Blood pressure critically low", "ICU"));

        alerts.add(new PatientAlert("P002", "Jane Smith", AlertType.MEDICATION, Priority.MEDIUM,
                "Medication due in 15 minutes", "Cardiology"));

        alerts.add(new PatientAlert("P003", "Bob Johnson", AlertType.VITAL_SIGNS, Priority.LOW,
                "Heart rate stable", "General Ward"));

        alerts.add(new PatientAlert("P004", "Alice Brown", AlertType.EMERGENCY, Priority.HIGH,
                "Patient experiencing chest pain", "Emergency"));

        alerts.add(new PatientAlert("P005", "Charlie Davis", AlertType.ROUTINE, Priority.LOW,
                "Daily checkup completed", "Outpatient"));

        alerts.add(new PatientAlert("P006", "Diana Wilson", AlertType.URGENT, Priority.HIGH,
                "Oxygen saturation dropping", "ICU"));

        alerts.add(new PatientAlert("P007", "Edward Martinez", AlertType.CRITICAL, Priority.HIGH,
                "Critical condition", "Emergency"));

        alerts.add(new PatientAlert("P008", "Fiona Garcia", AlertType.MEDICATION, Priority.MEDIUM,
                "Insulin administration required", "Cardiology"));

        return alerts;
    }
}
