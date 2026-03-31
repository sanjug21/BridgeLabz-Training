package temperature_alert_system;

class TemperatureReading {
    private String location;
    private double temperature;
    private String timestamp;

    public TemperatureReading(String location, double temperature, String timestamp) {
        this.location = location;
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("%s | Location: %s | Temperature: %.1f°C", timestamp, location, temperature);
    }
}
