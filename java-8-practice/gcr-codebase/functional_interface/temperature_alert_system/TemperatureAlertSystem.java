package temperature_alert_system;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TemperatureAlertSystem {

    public static void checkTemperatureAlert(TemperatureReading reading, Predicate<Double> alertCondition) {
        if (alertCondition.test(reading.getTemperature())) {
            System.out.println("ALERT! " + reading);
        } else {
            System.out.println("Normal: " + reading);
        }
    }

    public static void main(String[] args) {
        List<TemperatureReading> readings = new ArrayList<>();
        readings.add(new TemperatureReading("Server Room", 28.5, "10:00 AM"));
        readings.add(new TemperatureReading("Server Room", 32.0, "11:00 AM"));
        readings.add(new TemperatureReading("Data Center", 25.0, "10:30 AM"));
        readings.add(new TemperatureReading("Data Center", 35.5, "12:00 PM"));
        readings.add(new TemperatureReading("Control Room", 22.0, "09:00 AM"));

        double threshold = 30.0;
        Predicate<Double> highTemperature = temp -> temp > threshold;

        System.out.println("Temperature Alert System - Threshold: " + threshold + "°C");
        System.out.println("===================================================");

        for (TemperatureReading reading : readings) {
            checkTemperatureAlert(reading, highTemperature);
        }

        System.out.println("\nCritical Temperature Check (>35°C):");
        System.out.println("====================================");
        Predicate<Double> criticalTemperature = temp -> temp > 35.0;
        
        for (TemperatureReading reading : readings) {
            if (criticalTemperature.test(reading.getTemperature())) {
                System.out.println("CRITICAL! " + reading);
            }
        }
    }
}
