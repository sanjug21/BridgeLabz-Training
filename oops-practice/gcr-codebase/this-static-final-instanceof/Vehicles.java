public class Vehicles {
    public String ownerName;
    public String vehicleType;
    public static int registrtionFee=1000;
    public final String registrationNumber;

    public Vehicles(String ownerName, String vehicleType, String registrationNumber) {
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.registrationNumber = registrationNumber;
    }
    // Static method to set registration fee
    public static boolean setRegistrationFee(int fee) {
        registrtionFee = fee;
        return true;
    }
    // display method to display vehicle details
    public void displayDetails() {
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Registration Number: " + registrationNumber);
    }
    public static void main(String[] args) {
        Vehicles v1 = new Vehicles("Sanju", "Car", "REG123");
        Vehicles v2 = new Vehicles("Shubham", "Bike", "REG456");
       
        // Using instanceof to check object type
        System.out.println("v1 instanceof Vehicles: " + (v1 instanceof Vehicles));
        System.out.println("v2 instanceof Vehicles: " + (v2 instanceof Vehicles));


        System.out.println("Registration Fee: " + Vehicles.registrtionFee);
        // updated registration fee
        Vehicles.setRegistrationFee(2000);
        System.out.println("Updated Registration Fee: " + Vehicles.registrtionFee);

        // Display vehicle details
        v1.displayDetails();
        v2.displayDetails();

       }

}
