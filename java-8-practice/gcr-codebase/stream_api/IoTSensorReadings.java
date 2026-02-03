import java.util.ArrayList;
import java.util.List;

class SensorReading {
    String sensorId;
    double value;

    public SensorReading(String sensorId, double value) {
        this.sensorId = sensorId;
        this.value = value;
    }

    public String getSensorId() {
        return sensorId;
    }

    public double getValue() {
        return value;
    }
}

public class IoTSensorReadings {

    public static void main(String[] args) {
        List<SensorReading> readings = new ArrayList<>();
        readings.add(new SensorReading("TEMP-01", 22.5));
        readings.add(new SensorReading("TEMP-02", 35.8));
        readings.add(new SensorReading("TEMP-03", 28.3));
        readings.add(new SensorReading("TEMP-04", 41.2));
        readings.add(new SensorReading("TEMP-05", 19.7));
        readings.add(new SensorReading("TEMP-06", 38.9));

        double threshold = 30.0;

        System.out.println("Sensor Readings Above Threshold (" + threshold + "°C):");
        readings.stream()
                .filter(r -> r.getValue() > threshold)
                .forEach(r -> System.out.println(r.getSensorId() + ": " + r.getValue() + "°C"));
    }
}
