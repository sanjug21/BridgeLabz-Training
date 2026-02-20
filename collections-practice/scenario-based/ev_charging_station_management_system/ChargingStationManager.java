import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ChargingStationManager {
    private Map<Integer, ChargingSlot> chargingSlots;
    private Queue<Vehicle> waitingVehicles;

    public ChargingStationManager() {
        this.chargingSlots = new HashMap<>();
        this.waitingVehicles = new LinkedList<>();
    }

    public void addChargingSlot(int slotId) {
        chargingSlots.put(slotId, new ChargingSlot(slotId));
        System.out.println("Charging slot " + slotId + " added");
    }

    public void addToWaitingQueue(Vehicle vehicle) {
        waitingVehicles.offer(vehicle);
        System.out.println("Vehicle " + vehicle.vehicleNumber + " added to waiting queue");
    }

    public void allocateSlot(Vehicle vehicle) throws NoChargingSlotAvailableException {
        int availableSlot = findAvailableSlot();
        if (availableSlot == -1) {
            throw new NoChargingSlotAvailableException(
                "No charging slots available for vehicle " + vehicle.vehicleNumber
            );
        }

        ChargingSlot slot = chargingSlots.get(availableSlot);
        slot.allocateVehicle(vehicle);
        System.out.println("Vehicle " + vehicle.vehicleNumber + " allocated to Slot " + availableSlot);
    }

    public void allocateNextFromQueue() throws NoChargingSlotAvailableException {
        if (waitingVehicles.isEmpty()) {
            System.out.println("No vehicles in waiting queue");
            return;
        }

        Vehicle vehicle = waitingVehicles.poll();
        allocateSlot(vehicle);
    }

    private int findAvailableSlot() {
        for (Map.Entry<Integer, ChargingSlot> entry : chargingSlots.entrySet()) {
            if (!entry.getValue().isOccupied) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public void updateUnitsConsumed(int slotId, double units) {
        if (!chargingSlots.containsKey(slotId)) {
            System.out.println("Invalid slot ID: " + slotId);
            return;
        }

        ChargingSlot slot = chargingSlots.get(slotId);
        if (!slot.isOccupied) {
            System.out.println("Slot " + slotId + " is not occupied");
            return;
        }

        slot.unitsConsumed = units;
        System.out.println("Updated units for Slot " + slotId + ": " + units + " units");
    }

    public void releaseSlotAndCalculateBill(int slotId, PricingStrategy pricing) {
        if (!chargingSlots.containsKey(slotId)) {
            System.out.println("Invalid slot ID: " + slotId);
            return;
        }

        ChargingSlot slot = chargingSlots.get(slotId);
        if (!slot.isOccupied) {
            System.out.println("Slot " + slotId + " is already empty");
            return;
        }

        double bill = pricing.calculateBill(slot.unitsConsumed);

        System.out.println("\n--- Charging Bill ---");
        System.out.println("Vehicle: " + slot.currentVehicle.vehicleNumber);
        System.out.println("Owner: " + slot.currentVehicle.ownerName);
        System.out.println("Slot: " + slotId);
        System.out.println("Units Consumed: " + slot.unitsConsumed);
        System.out.println("Pricing: " + pricing.getDescription());
        System.out.println("Total Bill: Rs. " + bill);

        slot.releaseSlot();
        System.out.println("Slot " + slotId + " released");
    }

    public void displaySlotStatus() {
        System.out.println("\n--- Charging Slot Status ---");
        for (ChargingSlot slot : chargingSlots.values()) {
            System.out.println(slot);
        }
    }

    public void displayWaitingQueue() {
        System.out.println("\n--- Waiting Queue ---");
        if (waitingVehicles.isEmpty()) {
            System.out.println("No vehicles waiting");
            return;
        }

        int count = 1;
        for (Vehicle vehicle : waitingVehicles) {
            System.out.println(count + ". " + vehicle);
            count++;
        }
    }

    public void displayStatistics() {
        System.out.println("\n--- Station Statistics ---");
        System.out.println("Total Slots: " + chargingSlots.size());

        int occupied = 0;
        for (ChargingSlot slot : chargingSlots.values()) {
            if (slot.isOccupied) occupied++;
        }

        System.out.println("Occupied Slots: " + occupied);
        System.out.println("Available Slots: " + (chargingSlots.size() - occupied));
        System.out.println("Vehicles Waiting: " + waitingVehicles.size());
    }
}
