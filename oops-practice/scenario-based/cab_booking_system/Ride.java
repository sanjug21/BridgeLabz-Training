package cab_booking_system;

public class Ride {
    private String rideId;
    private User user;
    private Driver driver;
    public double distance;
    private double fare;
    private FareCalculator fareCalculator;



    Ride(String rideId, User user, Driver driver, double distance, FareCalculator fareCalculator) {
        this.rideId = rideId;
        this.user = user;
        this.driver = driver;
        this.distance = distance;
        this.fareCalculator = fareCalculator;
        this.fare=fareCalculator.calculateFare(distance);
    }

    public String getRideId() {
        return rideId;
    }

    public User getUser() {
        return user;
    }

    public Driver getDriver() {
        return driver;
    }

    public double getDistance() {
        return distance;
    }

    public double getFare() {
        return fare;
    }
    public FareCalculator getFareCalculator() {
        return fareCalculator;
    }


    @Override
    public String toString() {
        return "Ride{" +
               "rideId='" + rideId + '\'' +
               ", user=" + user +
               ", driver=" + driver +
               ", distance=" + distance +
               ", fare=" + fare +
               '}';
    
    }


    
}