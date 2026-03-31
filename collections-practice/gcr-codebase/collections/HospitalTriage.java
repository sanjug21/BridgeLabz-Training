import java.util.PriorityQueue;
import java.util.Scanner;

class Patient {
    String name;
    int severity;

    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    @Override
    public String toString() {
        return name + " (Severity: " + severity + ")";
    }
}

public class HospitalTriage {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Create PriorityQueue with custom Comparator (Higher severity first)
        PriorityQueue<Patient> triageQueue = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.severity, p1.severity));

        // 2. Input Patients
        System.out.print("Enter number of patients: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Name for patient " + (i + 1) + ": ");
            String name = sc.next();
            System.out.print("Enter Severity for patient " + (i + 1) + " (Higher number = Higher priority): ");
            int severity = sc.nextInt();
            triageQueue.add(new Patient(name, severity));
        }

        // 3. Process Patients
        System.out.println("\n--- Treatment Order ---");
        while (!triageQueue.isEmpty()) {
            System.out.println("Treating: " + triageQueue.poll());
        }

        sc.close();
    }
}