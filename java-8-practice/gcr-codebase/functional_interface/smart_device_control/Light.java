package smart_device_control;

class Light implements SmartDevice {
    private String location;
    private boolean isOn;

    public Light(String location) {
        this.location = location;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(location + " Light: Turned ON");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(location + " Light: Turned OFF");
    }
}
