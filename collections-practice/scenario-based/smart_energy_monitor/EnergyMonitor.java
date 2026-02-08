import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class EnergyMonitor {
    private Map<LocalDate, List<Double>> dailyEnergyData;
    private static final double MAX_VALID_READING = 100.0;
    private static final double MIN_VALID_READING = 0.0;

    public EnergyMonitor() {
        dailyEnergyData = new HashMap<>();
    }

    public void recordEnergyReading(LocalDate date, double reading) throws InvalidEnergyReadingException {
        if (reading < MIN_VALID_READING || reading > MAX_VALID_READING) {
            throw new InvalidEnergyReadingException(
                    "Invalid reading: " + reading + ". Expected value between " +
                    MIN_VALID_READING + " and " + MAX_VALID_READING);
        }

        dailyEnergyData.putIfAbsent(date, new ArrayList<>());
        dailyEnergyData.get(date).add(reading);
        System.out.println("Energy reading recorded for " + date + ": " + reading + " kWh");
    }

    public double getDailyAverage(LocalDate date) {
        List<Double> readings = dailyEnergyData.get(date);
        if (readings == null || readings.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (double reading : readings) {
            sum += reading;
        }
        return sum / readings.size();
    }

    public double getDailyTotal(LocalDate date) {
        List<Double> readings = dailyEnergyData.get(date);
        if (readings == null || readings.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (double reading : readings) {
            sum += reading;
        }
        return sum;
    }

    public double getMonthlyAverage(YearMonth yearMonth) {
        double totalSum = 0;
        int dayCount = 0;

        for (LocalDate date : dailyEnergyData.keySet()) {
            if (YearMonth.from(date).equals(yearMonth)) {
                totalSum += getDailyTotal(date);
                dayCount++;
            }
        }

        if (dayCount == 0) {
            return 0.0;
        }
        return totalSum / dayCount;
    }

    public double getMonthlyTotal(YearMonth yearMonth) {
        double totalSum = 0;

        for (LocalDate date : dailyEnergyData.keySet()) {
            if (YearMonth.from(date).equals(yearMonth)) {
                totalSum += getDailyTotal(date);
            }
        }

        return totalSum;
    }

    public void displayDailyReport(LocalDate date) {
        List<Double> readings = dailyEnergyData.get(date);
        System.out.println("\n=== Daily Report for " + date + " ===");

        if (readings == null || readings.isEmpty()) {
            System.out.println("No readings recorded for this date");
            return;
        }

        System.out.println("Readings:");
        for (int i = 0; i < readings.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + readings.get(i) + " kWh");
        }
        System.out.println("Daily Total: " + getDailyTotal(date) + " kWh");
        System.out.println("Daily Average: " + String.format("%.2f", getDailyAverage(date)) + " kWh");
    }

    public void displayMonthlyReport(YearMonth yearMonth) {
        System.out.println("\n=== Monthly Report for " + yearMonth + " ===");
        System.out.println("Monthly Total: " + String.format("%.2f", getMonthlyTotal(yearMonth)) + " kWh");
        System.out.println("Monthly Average: " + String.format("%.2f", getMonthlyAverage(yearMonth)) + " kWh per day");
    }

    public void displayAllReadings() {
        System.out.println("\n=== All Energy Readings ===");
        for (LocalDate date : dailyEnergyData.keySet()) {
            System.out.println(date + ": " + getDailyTotal(date) + " kWh (avg: " +
                    String.format("%.2f", getDailyAverage(date)) + " kWh)");
        }
    }
}
