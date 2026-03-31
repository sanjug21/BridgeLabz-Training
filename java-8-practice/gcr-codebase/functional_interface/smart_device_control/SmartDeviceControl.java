package smart_device_control;

public class SmartDeviceControl {

    public static void main(String[] args) {
        SmartDevice livingRoomLight = new Light("Living Room");
        SmartDevice bedroomAC = new AirConditioner("Bedroom");
        SmartDevice kitchenTV = new Television("Kitchen");

        System.out.println("Smart Home Control System:");
        System.out.println("-------------------------");

        livingRoomLight.turnOn();
        bedroomAC.turnOn();
        kitchenTV.turnOn();

        System.out.println();

        livingRoomLight.turnOff();
        bedroomAC.turnOff();
        kitchenTV.turnOff();
    }
}
