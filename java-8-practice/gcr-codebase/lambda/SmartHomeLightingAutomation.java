import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@FunctionalInterface
interface LightBehavior {
    void activate(String roomName);
}

class SmartLight {
    private String location;
    private boolean isOn;
    private int brightness;

    public SmartLight(String location) {
        this.location = location;
        this.isOn = false;
        this.brightness = 0;
    }

    public void turnOn(int brightness) {
        this.isOn = true;
        this.brightness = brightness;
        System.out.println(location + " light turned ON at " + brightness + "% brightness");
    }

    public void turnOff() {
        this.isOn = false;
        this.brightness = 0;
        System.out.println(location + " light turned OFF");
    }

    public String getLocation() {
        return location;
    }
}

public class SmartHomeLightingAutomation {

    public static void main(String[] args) {
        Map<String, SmartLight> lights = new HashMap<>();
        lights.put("Living Room", new SmartLight("Living Room"));
        lights.put("Bedroom", new SmartLight("Bedroom"));
        lights.put("Kitchen", new SmartLight("Kitchen"));

        LightBehavior motion = room -> {
            SmartLight light = lights.get(room);
            if (light != null) {
                light.turnOn(100);
            }
        };

        LightBehavior timeBased = room -> {
            SmartLight light = lights.get(room);
            if (light != null) {
                int hour = LocalTime.now().getHour();
                int brightness = hour < 18 ? 70 : 40;
                light.turnOn(brightness);
            }
        };

        LightBehavior voice = command -> {
            String[] parts = command.split(":");
            String room = parts[0];
            String action = parts.length > 1 ? parts[1] : "on";
            SmartLight light = lights.get(room);
            if (light != null) {
                if ("off".equalsIgnoreCase(action)) {
                    light.turnOff();
                } else if ("dim".equalsIgnoreCase(action)) {
                    light.turnOn(30);
                } else {
                    light.turnOn(100);
                }
            }
        };

        motion.activate("Living Room");
        timeBased.activate("Bedroom");
        voice.activate("Kitchen:dim");
    }
}
