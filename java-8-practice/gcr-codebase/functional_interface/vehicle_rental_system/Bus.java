package vehicle_rental_system;

class Bus implements Vehicle {
    private String route;
    private int capacity;
    private double tripRate;
    private boolean isRented;

    public Bus(String route, int capacity, double tripRate) {
        this.route = route;
        this.capacity = capacity;
        this.tripRate = tripRate;
        this.isRented = false;
    }

    @Override
    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println("Bus Rented: Route " + route + " | Capacity: " + capacity + " | Trip Rate: $" + tripRate);
        } else {
            System.out.println("Bus on Route " + route + " is already rented");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println("Bus Returned: Route " + route + " | Thank you!");
        } else {
            System.out.println("Bus on Route " + route + " was not rented");
        }
    }
}
