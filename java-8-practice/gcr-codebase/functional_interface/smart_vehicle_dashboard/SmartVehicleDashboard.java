package smart_vehicle_dashboard;

public class SmartVehicleDashboard {

    public static void main(String[] args) {
        VehicleDashboard tesla = new ElectricCar("Tesla Model 3");
        VehicleDashboard sedan = new PetrolCar("Honda City");

        System.out.println("Smart Vehicle Dashboard");
        System.out.println("=======================");

        tesla.displaySpeed(88);
        tesla.displayBatteryPercentage(72);

        System.out.println();

        sedan.displaySpeed(65);
        sedan.displayBatteryPercentage(0);
    }
}
