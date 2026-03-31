

import java.util.ArrayList;
import java.util.List;

class Agent {
    private String agentId;
    private String name;
    private double latitude;
    private double longitude;
    private boolean available;
    private List<Order> assignedOrders;

    public Agent(String agentId, String name, double latitude, double longitude) {
        this.agentId = agentId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = true;
        this.assignedOrders = new ArrayList<>();
    }

    public String getAgentId() {
        return agentId;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Order> getAssignedOrders() {
        return assignedOrders;
    }

    public void assignOrder(Order order) {
        assignedOrders.add(order);
        this.available = false;
    }

    public void completeOrder(Order order) {
        assignedOrders.remove(order);
        if (assignedOrders.isEmpty()) {
            this.available = true;
        }
    }

    public double calculateDistance(double lat, double lon) {
        return Math.sqrt(Math.pow(this.latitude - lat, 2) + Math.pow(this.longitude - lon, 2));
    }

    @Override
    public String toString() {
        return "Agent{" +
               "agentId='" + agentId + '\'' +
               ", name='" + name + '\'' +
               ", location=(" + latitude + ", " + longitude + ")" +
               ", available=" + available +
               ", assignedOrders=" + assignedOrders.size() +
               '}';
    }
}
