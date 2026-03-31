package smart_device_control;

class Television implements SmartDevice {
    private String location;
    private boolean isOn;

    public Television(String location) {
        this.location = location;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(location + " TV: Turned ON | Default channel: HBO");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(location + " TV: Turned OFF");
    }
}
