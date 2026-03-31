import java.time.LocalDate;

abstract class Violation {
    String violationType;
    LocalDate date;
    String location;

    public Violation(String violationType, LocalDate date, String location) {
        this.violationType = violationType;
        this.date = date;
        this.location = location;
    }

    // Polymorphism - each violation type has different fine
    public abstract double calculateFine();

    @Override
    public String toString() {
        return violationType + " | " + date + " | " + location + " | Fine: ₹" + calculateFine();
    }
}

class SpeedingViolation extends Violation {
    int speedLimit;
    int actualSpeed;

    public SpeedingViolation(LocalDate date, String location, int speedLimit, int actualSpeed) {
        super("Speeding", date, location);
        this.speedLimit = speedLimit;
        this.actualSpeed = actualSpeed;
    }

    @Override
    public double calculateFine() {
        int excess = actualSpeed - speedLimit;
        if (excess <= 10) return 500;
        else if (excess <= 20) return 1000;
        else if (excess <= 30) return 2000;
        else return 3000;
    }

    @Override
    public String toString() {
        return super.toString() + " (Speed: " + actualSpeed + "/" + speedLimit + ")";
    }
}

class RedLightViolation extends Violation {
    public RedLightViolation(LocalDate date, String location) {
        super("Red Light Jumped", date, location);
    }

    @Override
    public double calculateFine() {
        return 1000;
    }
}

class ParkingViolation extends Violation {
    String parkingZone;

    public ParkingViolation(LocalDate date, String location, String parkingZone) {
        super("Illegal Parking", date, location);
        this.parkingZone = parkingZone;
    }

    @Override
    public double calculateFine() {
        if (parkingZone.equals("No Parking")) return 500;
        else if (parkingZone.equals("Reserved")) return 800;
        else return 300;
    }

    @Override
    public String toString() {
        return super.toString() + " (Zone: " + parkingZone + ")";
    }
}

class HelmetViolation extends Violation {
    public HelmetViolation(LocalDate date, String location) {
        super("No Helmet", date, location);
    }

    @Override
    public double calculateFine() {
        return 500;
    }
}

class SeatbeltViolation extends Violation {
    public SeatbeltViolation(LocalDate date, String location) {
        super("No Seatbelt", date, location);
    }

    @Override
    public double calculateFine() {
        return 500;
    }
}
