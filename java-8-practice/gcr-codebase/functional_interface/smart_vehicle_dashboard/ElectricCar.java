package smart_vehicle_dashboard;

class ElectricCar implements VehicleDashboard {
    private String model;

    public ElectricCar(String model) {
        this.model = model;
    }

    @Override
    public void displaySpeed(int speedKmph) {
        System.out.println(model + " Speed: " + speedKmph + " km/h");
    }

    @Override
    public void displayBatteryPercentage(int batteryPercentage) {
        System.out.println(model + " Battery: " + batteryPercentage + "%");
    }
}
