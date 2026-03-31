import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlightUtil flightUtil = new FlightUtil();
        System.out.println("Enter flight details:");
        String []input=scanner.nextLine().split(":");
        scanner.close();
        String flightNo=input[0];
        String flightName=input[1];
        int passengerCount=Integer.parseInt(input[2]);
        double currentFuelLevel=Double.parseDouble(input[3]);
        try {
            if(!flightUtil.validateFlightNumber(flightNo)) {
                System.out.println("Flight Number "+flightNo+" is invalid.");
                return;
            }

            if(!flightUtil.validateFlightName(flightName)) {
                System.out.println("Flight Name "+flightName+" is invalid.");
                return;
            }


            if(!flightUtil.validatePassengerCount(flightName, passengerCount)) {
                System.out.println("Passenger count "+passengerCount+" is invalid.");
                return;
            }

            double fuelToFill = flightUtil.calculateFuelToFillTank(flightName, currentFuelLevel);
            System.out.println("Fuel required to fill the tank: " + fuelToFill + " liters.");

        } catch (InvalidFlightException e) {
            System.err.println(e.getMessage());
        }
    }
}
