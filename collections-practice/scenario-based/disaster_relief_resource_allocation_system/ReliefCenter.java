import java.util.HashMap;
import java.util.Map;

class ReliefCenter {
    String centerId;
    String location;
    Map<String, Integer> resources;

    public ReliefCenter(String centerId, String location) {
        this.centerId = centerId;
        this.location = location;
        this.resources = new HashMap<>();
    }

    public void addResource(String item, int quantity) {
        resources.put(item, resources.getOrDefault(item, 0) + quantity);
    }

    public boolean hasResource(String item, int quantity) {
        return resources.getOrDefault(item, 0) >= quantity;
    }

    public void allocateResource(String item, int quantity) throws InsufficientResourceException {
        if (!hasResource(item, quantity)) {
            throw new InsufficientResourceException(
                "Insufficient " + item + " at " + centerId + 
                " (Available: " + resources.getOrDefault(item, 0) + ", Requested: " + quantity + ")"
            );
        }
        resources.put(item, resources.get(item) - quantity);
    }

    @Override
    public String toString() {
        return centerId + " | " + location + " | Resources: " + resources.size() + " types";
    }

    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(centerId).append(" | ").append(location).append("\n");
        sb.append("Resources:\n");
        for (Map.Entry<String, Integer> entry : resources.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
