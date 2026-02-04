package vehicle_rental_system;

class Bike implements Vehicle {
    private String brand;
    private double hourlyRate;
    private boolean isRented;

    public Bike(String brand, double hourlyRate) {
        this.brand = brand;
        this.hourlyRate = hourlyRate;
        this.isRented = false;
    }

    @Override
    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println("Bike Rented: " + brand + " | Hourly Rate: $" + hourlyRate);
        } else {
            System.out.println("Bike " + brand + " is already rented");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Bike Returned: " + brand + " | Thank you!");
        } else {
            System.out.println("Bike " + brand + " was not rented");
        }
    }
}
