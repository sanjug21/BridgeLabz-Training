import java.util.*;

// Interface to calculate fare based on distance
interface FareCalculator {
    double calculateFare(double distance);
}

class NotAvailableException extends Exception {
    public NotAvailableException(String message) {
        super(message);
    }
}
class InvalidDriverIdException extends Exception {
    public InvalidDriverIdException(String message) {
        super(message);
    }
}


// We have implemented polymorphism here

// Concrete classes implementing FareCalculator in normal pricing
class NormalPricing implements FareCalculator {
    public double calculateFare(double distance) {
        return distance * 10; // Base rate: 10 Rs/km
    }
}

// Concrete classes implementing FareCalculator in peak pricing
class PeakPricing implements FareCalculator {
    public double calculateFare(double distance) {
        return (distance * 10) * 1.5; // Peak rate: 1.5x surge
    }
}

// User class to represent a user
class User{
    String name;
    String phoneNumber;
    int age;
    List<Ride> rideHistory = new ArrayList<>();

    User(String name, String phoneNumber,int age) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age=age;
    }

    void displayUserDetails() {
        System.out.println("Name:            " + name);
        System.out.println("Phone Number:    " + phoneNumber);
        System.out.println("Age:             " + age);

    }
    void addRideToHistory(Ride ride) {
        rideHistory.add(ride);
    }

}
class Driver extends User{
    String licenseNumber;
    String vehicalName;
    double rating;
    boolean isAvailable;

    Driver(String name, String phoneNumber,int age,String licenseNumber, String vehicalName,double rating) {
        super(name, phoneNumber,age);
        this.licenseNumber=licenseNumber;
        this.vehicalName=vehicalName;
        this.rating=rating;
        this.isAvailable = true;
    }
    void displayDriverDetails() {
        super.displayUserDetails();
        System.out.println("License Number:  " + licenseNumber);
        System.out.println("Vehical Name:    " + vehicalName);
        System.out.println("Rating:          " + rating);
        System.out.println("Availability:    " + (isAvailable ? "Available" : "Booked"));
        System.out.println("---------------------------------------------------------");
    }
}

 class Ride {
    int rideId;
    Driver driver;
    User user;
    double distance;
    double fare;

    Ride(int rideId, Driver driver, User user, double distance) {
        this.rideId = rideId;
        this.driver = driver;
        this.user = user;
        this.distance = distance;
    }


    void displayRideDetails() {
        System.out.println("Ride Details:");
        System.out.println("Driver Details:");
        driver.displayDriverDetails();
        System.out.println("User Details:");
        user.displayUserDetails();
        System.out.println("Distance:        " + distance + " km");
        System.out.println("Fare: Rs         " + fare);
    }

}

class CabRide extends Ride {
    CabRide(int rideId, Driver driver, User user, double distance, FareCalculator fareCalculator) {
        super(rideId, driver, user, distance);
        this.fare = fareCalculator.calculateFare(distance);
    }
}


public class CabBooking {
    static Scanner sc = new Scanner(System.in);
    List<Driver> drivers;
    List<User> passengers;
    List<Ride> rides;



    CabBooking(){
        this.drivers=new ArrayList<>();
        this.passengers=new ArrayList<>();
        this.rides=new ArrayList<>();
        addDriver();
    }

    void addDriver(){
        // added 10 drivers by defalut
        drivers.add(new Driver("Rakesh", "9876543210", 35, "DL12345", "Toyota Camry", 4.8));
        drivers.add(new Driver("Suresh", "9876543211", 40, "DL12346", "Honda Civic", 4.5));
        drivers.add(new Driver("Mahesh", "9876543212", 28, "DL12347", "Maruti Swift", 4.2));
        drivers.add(new Driver("Dinesh", "9876543213", 32, "DL12348", "Hyundai Creta", 4.9));
        drivers.add(new Driver("Ganesh", "9876543214", 45, "DL12349", "Tata Nexon", 4.0));
        drivers.add(new Driver("Rajesh", "9876543215", 30, "DL12350", "Mahindra XUV300", 4.7));
        drivers.add(new Driver("Prakash", "9876543216", 38, "DL12351", "Kia Seltos", 4.6));
        drivers.add(new Driver("Vikas", "9876543217", 25, "DL12352", "Renault Kwid", 4.1));
        drivers.add(new Driver("Naveen", "9876543218", 50, "DL12353", "Ford EcoSport", 4.4));
        drivers.add(new Driver("Pawan", "9876543219", 33, "DL12354", "Nissan Magnite", 4.3));
    }
    void addDriverManually(String name,String phoneNumber,int age,String licenseNumber,String vehicalName){
        drivers.add(new Driver(name,phoneNumber,age,licenseNumber,vehicalName,4.0));
    }
    void addPassenger(String name,String phoneNumber,int age){
        passengers.add(new User(name,phoneNumber,age));
    }   
    void addRide(Ride ride){
        rides.add(ride);
    }
    void displayDrivers(){
        for(int i=0;i<drivers.size();i++){
            System.out.println("Driver id "+(i+1));
            drivers.get(i).displayDriverDetails();
        }
    }
    public static void main(String[] args) {
        
        CabBooking cabBooking=new CabBooking();

        System.out.println("======= Welcome to Online Ride Booking System =======");
        boolean open=true;
        while (open) {
            System.out.println("Choose an action:");
            System.out.println("1. User");
            System.out.println("2. Driver");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");         
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    cabBooking.UserDashBoard();
                    break;
                case 2:
                    cabBooking.DriverDashBoard();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }

    }

    public void UserDashBoard() {
        System.out.println("Enter yor Details to continue:");
        System.out.println("Enter name: ");
        String name=sc.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber=sc.nextLine();
        System.out.println("Enter age: ");
        int age=sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.println("Enter distance to travel: ");
        double distance=sc.nextDouble();
        sc.nextLine(); // consume newline
        User user=new User(name,phoneNumber,age);


        System.out.println("Here is the list of available drivers:");
        displayDrivers();
        boolean isBooked=false;
        while(!isBooked)
        
        try{
            System.out.println("Choose Driver id to book the cab");
            int driverId=sc.nextInt();
            sc.nextLine(); // consume newline
            // validate driver id
            if(driverId<1 || driverId>drivers.size())throw new InvalidDriverIdException("Invalid driver id");
            // validate driver availability
            Driver driver=drivers.get(driverId-1);
            // validate driver availability
            if(!driver.isAvailable)throw new NotAvailableException("Driver is not available");
            // generate random pricing and ride id
            int price=randomPricing();
            int rideId = generateRideId();
            FareCalculator fareCalculator=price==1?new NormalPricing():new PeakPricing();

            // book ride
            CabRide ride=new CabRide(rideId,driver,user,distance,fareCalculator);
            // update driver availability
            driver.isAvailable = false;
            // add ride to history of user and driver 
            addRide(ride);
            user.addRideToHistory(ride);
            driver.addRideToHistory(ride);
            System.out.println("Ride booked successfully.");
            ride.displayRideDetails();
            isBooked=true;

            
        }catch(InvalidDriverIdException e){
            System.out.println(e.getMessage());
            
        }
        catch(NotAvailableException e){
            System.out.println(e.getMessage());
            
        }

    }

    public void DriverDashBoard() {
        System.out.println("Enter yor Details to continue:");
        System.out.println("Enter name: ");
        String name=sc.nextLine();
        System.out.println("Enter your phone number: ");
        String phoneNumber=sc.nextLine();
        System.out.println("Enter your age: ");
        int age=sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.println("Enter your license number: ");
        String licenseNumber=sc.nextLine();
        System.out.println("Enter your vehical name: ");
        String vehicalName=sc.nextLine();
        addDriverManually(name,phoneNumber,age,licenseNumber,vehicalName);
        System.out.println("Driver added successfully.");

    }

    public int randomPricing(){
        return Math.random()*10%2==0?1:2;
    }
    public int generateRideId(){
        return (int)Math.random()*10000;
    }
    
}
