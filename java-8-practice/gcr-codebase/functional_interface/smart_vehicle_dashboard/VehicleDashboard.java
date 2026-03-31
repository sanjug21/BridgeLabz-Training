package smart_vehicle_dashboard;

public interface VehicleDashboard {
    void displaySpeed(int speedKmph);

    default void displayBatteryPercentage(int batteryPercentage) {
        System.out.println("Battery data not available for this vehicle.");
    }
}
