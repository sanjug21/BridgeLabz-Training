import java.time.LocalDate;
import java.time.YearMonth;

public class SmartEnergyApp {

    public static void main(String[] args) {
        EnergyMonitor monitor = new EnergyMonitor();

        LocalDate date1 = LocalDate.of(2026, 2, 1);
        LocalDate date2 = LocalDate.of(2026, 2, 2);
        LocalDate date3 = LocalDate.of(2026, 2, 3);
        LocalDate date4 = LocalDate.of(2026, 1, 28);

        System.out.println("=== Recording Energy Readings ===");

        try {
            monitor.recordEnergyReading(date1, 15.5);
            monitor.recordEnergyReading(date1, 18.2);
            monitor.recordEnergyReading(date1, 16.8);

            monitor.recordEnergyReading(date2, 12.3);
            monitor.recordEnergyReading(date2, 14.7);

            monitor.recordEnergyReading(date3, 20.1);
            monitor.recordEnergyReading(date3, 22.5);
            monitor.recordEnergyReading(date3, 19.9);

            monitor.recordEnergyReading(date4, 25.0);
            monitor.recordEnergyReading(date4, 24.5);

            System.out.println("\nTrying to record invalid reading...");
            monitor.recordEnergyReading(date1, 150.0);

        } catch (InvalidEnergyReadingException e) {
            System.out.println("Reading Error: " + e.getMessage());
        }

        monitor.displayAllReadings();

        monitor.displayDailyReport(date1);
        monitor.displayDailyReport(date2);
        monitor.displayDailyReport(date3);

        YearMonth february = YearMonth.of(2026, 2);
        YearMonth january = YearMonth.of(2026, 1);

        monitor.displayMonthlyReport(february);
        monitor.displayMonthlyReport(january);

        System.out.println("\n=== Energy Analysis ===");
        System.out.println("February 1st Total: " + String.format("%.2f", monitor.getDailyTotal(date1)) + " kWh");
        System.out.println("February 1st Average: " + String.format("%.2f", monitor.getDailyAverage(date1)) + " kWh");
    }
}
