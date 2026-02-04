package vehicle_rental_system;

class Car implements Vehicle {
    private String model;
    private double dailyRate;
    private boolean isRented;

    public Car(String model, double dailyRate) {
        this.model = model;
        this.dailyRate = dailyRate;
        this.isRented = false;
    }

    @Override
    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println("Car Rented: " + model + " | Daily Rate: $" + dailyRate);
        } else {
            System.out.println("Car " + model + " is already rented");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Car Returned: " + model + " | Thank you!");
        } else {
            System.out.println("Car " + model + " was not rented");
        }
    }
}
