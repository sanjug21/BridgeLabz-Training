package smart_device_control;

class AirConditioner implements SmartDevice {
    private String room;
    private boolean isOn;

    public AirConditioner(String room) {
        this.room = room;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(room + " AC: Turned ON | Temperature set to 24°C");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(room + " AC: Turned OFF");
    }
}
