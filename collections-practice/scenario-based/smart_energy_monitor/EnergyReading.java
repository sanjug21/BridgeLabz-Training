import java.time.LocalDate;

class EnergyReading {
    private LocalDate date;
    private double reading;

    public EnergyReading(LocalDate date, double reading) {
        this.date = date;
        this.reading = reading;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getReading() {
        return reading;
    }

    @Override
    public String toString() {
        return "EnergyReading{" +
                "date=" + date +
                ", reading=" + reading + " kWh" +
                '}';
    }
}
