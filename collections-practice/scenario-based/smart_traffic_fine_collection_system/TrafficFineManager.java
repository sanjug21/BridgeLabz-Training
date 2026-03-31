
import java.time.YearMonth;
import java.util.*;
import java.util.regex.Pattern;

public class TrafficFineManager {
    private Map<String, List<Violation>> violationRecords;
    private static final Pattern VEHICLE_PATTERN = Pattern.compile("^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$");
    private static final int REPEAT_OFFENDER_THRESHOLD = 3;
    private static final double REPEAT_OFFENDER_PENALTY = 0.5; // 50% extra

    public TrafficFineManager() {
        this.violationRecords = new HashMap<>();
    }

    // Validate vehicle number format
    public void validateVehicleNumber(String vehicleNumber) throws InvalidVehicleException {
        if (vehicleNumber == null || vehicleNumber.isEmpty()) {
            throw new InvalidVehicleException("Vehicle number cannot be empty");
        }

        if (!VEHICLE_PATTERN.matcher(vehicleNumber).matches()) {
            throw new InvalidVehicleException(
                "Invalid vehicle number format. Expected: XX00XX0000 (e.g., MH12AB1234)"
            );
        }
    }

    // Add violation to the system
    public void recordViolation(String vehicleNumber, Violation violation) throws InvalidVehicleException {
        validateVehicleNumber(vehicleNumber);

        violationRecords.putIfAbsent(vehicleNumber, new ArrayList<>());
        violationRecords.get(vehicleNumber).add(violation);

        System.out.println("Violation recorded for " + vehicleNumber);
    }

    // Check if vehicle is a repeat offender
    public boolean isRepeatOffender(String vehicleNumber) {
        return violationRecords.containsKey(vehicleNumber) &&
               violationRecords.get(vehicleNumber).size() >= REPEAT_OFFENDER_THRESHOLD;
    }

    // Calculate total fine for a vehicle with repeat offender penalty
    public double calculateTotalFine(String vehicleNumber) throws InvalidVehicleException {
        validateVehicleNumber(vehicleNumber);

        if (!violationRecords.containsKey(vehicleNumber)) {
            return 0.0;
        }

        List<Violation> violations = violationRecords.get(vehicleNumber);
        double baseFine = 0;

        for (Violation v : violations) {
            baseFine += v.calculateFine();
        }

        // Apply repeat offender penalty
        if (isRepeatOffender(vehicleNumber)) {
            double penalty = baseFine * REPEAT_OFFENDER_PENALTY;
            return baseFine + penalty;
        }

        return baseFine;
    }

    // Get violation history for a vehicle
    public void printViolationHistory(String vehicleNumber) throws InvalidVehicleException {
        validateVehicleNumber(vehicleNumber);

        if (!violationRecords.containsKey(vehicleNumber)) {
            System.out.println("No violations found for " + vehicleNumber);
            return;
        }

        List<Violation> violations = violationRecords.get(vehicleNumber);
        System.out.println("\n--- Violation History for " + vehicleNumber + " ---");
        System.out.println("Total Violations: " + violations.size());
        
        if (isRepeatOffender(vehicleNumber)) {
            System.out.println("WARNING: REPEAT OFFENDER - Additional 50% penalty applied!");
        }

        for (int i = 0; i < violations.size(); i++) {
            System.out.println((i + 1) + ". " + violations.get(i));
        }

        double totalFine = calculateTotalFine(vehicleNumber);
        System.out.println("Total Fine: ₹" + totalFine);
    }

    // Generate monthly report
    public void generateMonthlyReport(int year, int month) {
        YearMonth targetMonth = YearMonth.of(year, month);
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("    MONTHLY TRAFFIC VIOLATION REPORT");
        System.out.println("    " + targetMonth.getMonth() + " " + year);
        System.out.println("═══════════════════════════════════════════════");

        Map<String, Double> monthlyFines = new HashMap<>();
        Map<String, Integer> monthlyViolationCount = new HashMap<>();
        int totalViolations = 0;
        double totalRevenue = 0;

        for (Map.Entry<String, List<Violation>> entry : violationRecords.entrySet()) {
            String vehicleNumber = entry.getKey();
            List<Violation> violations = entry.getValue();

            double vehicleFine = 0;
            int violationCount = 0;

            for (Violation v : violations) {
                if (YearMonth.from(v.date).equals(targetMonth)) {
                    vehicleFine += v.calculateFine();
                    violationCount++;
                    totalViolations++;
                }
            }

            if (violationCount > 0) {
                // Apply repeat offender penalty for monthly total
                if (isRepeatOffender(vehicleNumber)) {
                    vehicleFine += vehicleFine * REPEAT_OFFENDER_PENALTY;
                }

                monthlyFines.put(vehicleNumber, vehicleFine);
                monthlyViolationCount.put(vehicleNumber, violationCount);
                totalRevenue += vehicleFine;
            }
        }

        if (monthlyFines.isEmpty()) {
            System.out.println("No violations recorded for this month.");
            return;
        }

        // Sort by fine amount (descending)
        List<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(monthlyFines.entrySet());
        sortedEntries.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        System.out.println("Vehicle Number | Violations | Total Fine | Status");
        System.out.println("───────────────────────────────────────────────────");
        for (Map.Entry<String, Double> entry : sortedEntries) {
            String vehicle = entry.getKey();
            String repeatFlag = isRepeatOffender(vehicle) ? "REPEAT OFFENDER" : "";
            System.out.printf("%-14s | %-10d | ₹%.2f | %s%n",
                vehicle,
                monthlyViolationCount.get(vehicle),
                entry.getValue(),
                repeatFlag
            );
        }

        System.out.println("═══════════════════════════════════════════════");
        System.out.println("Total Violations: " + totalViolations);
        System.out.println("Total Revenue: ₹" + totalRevenue);
        System.out.println("═══════════════════════════════════════════════");
    }

    // Print all records
    public void printAllRecords() {
        System.out.println("\n--- All Traffic Violation Records ---");
        if (violationRecords.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (String vehicleNumber : violationRecords.keySet()) {
            try {
                printViolationHistory(vehicleNumber);
            } catch (InvalidVehicleException e) {
                // Should not happen as we're iterating existing records
            }
        }
    }

    // Get statistics
    public void printStatistics() {
        System.out.println("\n--- System Statistics ---");
        System.out.println("Total Vehicles Tracked: " + violationRecords.size());

        Map<String, Integer> violationTypeCount = new HashMap<>();
        int totalViolations = 0;
        double totalFines = 0;

        for (List<Violation> violations : violationRecords.values()) {
            for (Violation v : violations) {
                violationTypeCount.put(v.violationType,
                    violationTypeCount.getOrDefault(v.violationType, 0) + 1);
                totalViolations++;
                totalFines += v.calculateFine();
            }
        }

        System.out.println("Total Violations: " + totalViolations);
        System.out.println("\nViolation Breakdown:");
        for (Map.Entry<String, Integer> entry : violationTypeCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Total Base Fines: ₹" + totalFines);
    }
}
