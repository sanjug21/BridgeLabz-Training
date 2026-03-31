package cab_booking_system;
import java.util.*;


public class CabBookingSystem {

    static List<Driver> drivers = new ArrayList<>();

    static List<Ride> rides = new ArrayList<>();

    // 10 dummy drivers 
    static void addDummyDrivers(){        
        drivers.add(new Driver("D001", "Sagar", "Swift Dezire"));
        drivers.add(new Driver("D002","Ashish","Hyundai i10"));
        drivers.add(new Driver("D003","Rahul","Tata Nexon"));
        drivers.add(new Driver("D004","Rohit","Mahindra XUV300"));
        drivers.add(new Driver("D005","Priya","Maruti Brezza"));
        drivers.add(new Driver("D006","Poonam","Kia Seltos"));
        drivers.add(new Driver("D007","Sunil","Toyota Innova"));
        drivers.add(new Driver("D008","Salman","Honda City"));
        drivers.add(new Driver("D009","Sheela","Maruti Ciaz"));
        drivers.add(new Driver("D010","Babita","Hyundai Verna"));
        drivers.get(0).setAvailable(false);
        drivers.get(1).setAvailable(false);
    }

   static void displayDrivers(){
        for(int i=0;i<drivers.size();i++){
            System.out.println("Driver "+(i+1));
            System.out.println(drivers.get(i));
        }
    }

    public static FareCalculator getFareCalculator(){
        int i=(int)((Math.random()*10)%2);
        FareCalculator fc;
        if(i==0){
            fc=new NormalPricing(10);
        }
        else{
            fc=new PeakPricing(20);
        }
        return fc;
    }


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        addDummyDrivers();
        User u1=new User("U001", "Sanju", "9876543210");

        System.out.println("Choose Drivers to book a Ride!");
        displayDrivers();
        int choice=sc.nextInt();
        if(choice <0 || choice>drivers.size()){
            System.out.println("Invalid Choice!");
            sc.close();
            return;
        }
        System.out.println("Enter Distance!");
        double distance=sc.nextDouble();
        try{
            Driver driver=drivers.get(choice-1);
            if(!driver.isAvailable())throw new NoDriverAvailableException("No Driver Available!");
            Ride r=new Ride("R001", u1, driver,distance , getFareCalculator());
            driver.setAvailable(false);
            u1.addRide(r);
            driver.addRide(r);
            System.out.println("Ride Booked Successfully!");
            System.out.println("Ride Details:");
            System.out.println(r);

            System.out.println("Driver Details:");
            System.out.println(driver);

            System.out.println("User Details:");
            System.out.println(u1);
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
           
        }

        sc.close();        
  
    }
}