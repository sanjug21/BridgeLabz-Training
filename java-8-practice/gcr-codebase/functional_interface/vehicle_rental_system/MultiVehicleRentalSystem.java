package vehicle_rental_system;

public class MultiVehicleRentalSystem {

    public static void main(String[] args) {
        Vehicle car = new Car("Toyota Camry", 50.00);
        Vehicle bike = new Bike("Honda CBR", 15.00);
        Vehicle bus = new Bus("City Center", 40, 200.00);

        System.out.println("Vehicle Rental System:");
        System.out.println("---------------------");

        car.rent();
        bike.rent();
        bus.rent();

        System.out.println();

        car.returnVehicle();
        bike.returnVehicle();
        bus.returnVehicle();
    }
}
