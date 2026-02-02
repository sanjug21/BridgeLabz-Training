import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FlightUtil {
    private List<String> flightNames=Arrays.asList(
        "spicejet", "vistara", "indigo","air arabia"
    );
    private Map<String,Integer> flightSeatCapacity=Map.of(
        "spicejet",396,
        "vistara",615,
        "indigo",230,
        "air arabia",130
    );
    private Map<String,Double> flightFuelCapacity=Map.of(
        "spicejet",200000.0,
        "vistara",300000.0,
        "indigo",250000.0,
        "air arabia",150000.0
    );
    public boolean validateFlightNumber(String flightNumber) {
        if (flightNumber == null || flightNumber.length() != 7) {
            return false;
        }
        return flightNumber.matches("FL-\\d{4}") &&
                Integer.parseInt(flightNumber.substring(3)) >= 1000 &&
                Integer.parseInt(flightNumber.substring(3)) <= 9999;
    }
    public boolean validateFlightName(String flightName) {
        return flightNames.contains(flightName.toLowerCase());
    }
    public boolean validatePassengerCount(String flightName, int passengerCount) throws InvalidFlightException {
        Integer capacity = flightSeatCapacity.get(flightName.toLowerCase());
        if ( capacity==null || passengerCount<=0 || passengerCount>capacity) {
            throw new InvalidFlightException("Passengers count " + passengerCount + " is invalid for " + flightName);
        }
        return passengerCount > 0 && passengerCount <= capacity;
    }
    public double calculateFuelToFillTank(String flightName, double currentFuelLevel) throws InvalidFlightException {
       
        if(currentFuelLevel<0 || currentFuelLevel>flightFuelCapacity.get(flightName.toLowerCase())){
            throw new InvalidFlightException("Invalid fuel level for " + flightName);
        }
        return flightFuelCapacity.get(flightName.toLowerCase()) - currentFuelLevel;
    }
        
}


