package smart_vehicle_dashboard;

class PetrolCar implements VehicleDashboard {
    private String model;

    public PetrolCar(String model) {
        this.model = model;
    }

    @Override
    public void displaySpeed(int speedKmph) {
        System.out.println(model + " Speed: " + speedKmph + " km/h");
    }
}
