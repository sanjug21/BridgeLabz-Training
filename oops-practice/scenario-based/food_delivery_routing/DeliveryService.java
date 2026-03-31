
import java.util.*;

class DeliveryService {
    private Queue<Order> orderQueue;
    private List<Agent> agents;

    public DeliveryService() {
        this.orderQueue = new LinkedList<>();
        this.agents = new ArrayList<>();
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    public void addOrder(Order order) {
        orderQueue.offer(order);
        System.out.println("Order added to queue: " + order.getOrderId());
    }

    public void assignOrder() throws NoAgentAvailableException {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in queue!");
            return;
        }

        Order order = orderQueue.poll();
        Agent nearestAgent = findNearestAvailableAgent(order);

        if (nearestAgent == null) {
            throw new NoAgentAvailableException("No agent available for order: " + order.getOrderId());
        }

        nearestAgent.assignOrder(order);
        order.setAssigned(true);
        System.out.println("Order " + order.getOrderId() + " assigned to Agent " + nearestAgent.getName());
    }

    private Agent findNearestAvailableAgent(Order order) {
        Agent nearestAgent = null;
        double minDistance = Double.MAX_VALUE;

        for (Agent agent : agents) {
            if (agent.isAvailable()) {
                double distance = agent.calculateDistance(order.getLatitude(), order.getLongitude());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestAgent = agent;
                }
            }
        }

        return nearestAgent;
    }

    public void cancelOrder(String orderId) {
        Iterator<Order> iterator = orderQueue.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderId().equals(orderId)) {
                iterator.remove();
                System.out.println("Order " + orderId + " cancelled from queue!");
                return;
            }
        }
        System.out.println("Order " + orderId + " not found in queue!");
    }

    public void viewActiveDeliveries() {
        System.out.println("\n===== Active Deliveries =====");
        boolean hasActive = false;
        for (Agent agent : agents) {
            if (!agent.isAvailable() && !agent.getAssignedOrders().isEmpty()) {
                hasActive = true;
                System.out.println("\nAgent: " + agent.getName());
                for (Order order : agent.getAssignedOrders()) {
                    System.out.println("  - " + order);
                }
            }
        }
        if (!hasActive) {
            System.out.println("No active deliveries!");
        }
    }

}

