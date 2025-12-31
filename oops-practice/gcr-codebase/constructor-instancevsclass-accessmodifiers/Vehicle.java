
public class Vehicle {
    String ownerName;
    String vehicleType;
    static double registrationFee=1000.0;
    public Vehicle(String ownerName,String vehicleType) {
        this.ownerName=ownerName;
        this.vehicleType=vehicleType;
    }
    public void displayVehicleDetails() {
        System.out.println("Owner: "+ownerName+", Type: "+vehicleType+", Registration Fee: "+registrationFee);
    }
    public static void updateRegistrationFee(double newFee) {
        registrationFee=newFee;
    }

    public static void main(String[] args) {
        // created 5 vehicle objects
        Vehicle v1=new Vehicle("Sanju","Car");
        Vehicle v2=new Vehicle("Shubham","Bike");
        Vehicle v3=new Vehicle("Ritu","Truck");
        Vehicle v4=new Vehicle("Shivani","Bus");
        Vehicle v5=new Vehicle("Krishna","Van");

        // display details of all vehicles
        v1.displayVehicleDetails(); 
        v2.displayVehicleDetails();
        v3.displayVehicleDetails();
        v4.displayVehicleDetails();
        v5.displayVehicleDetails();

        System.out.println("Registration fee: "+Vehicle.registrationFee);
        // update registration fee
        Vehicle.updateRegistrationFee(1500.0);
        System.out.println("Updated Registration fee: "+Vehicle.registrationFee);   
    }
}