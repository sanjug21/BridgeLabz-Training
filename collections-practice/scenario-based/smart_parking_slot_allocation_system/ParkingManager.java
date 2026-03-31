import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ParkingManager {
    private Map<Integer, ParkingSlot> parkingSlots;
    private Queue<Vehicle> waitingVehicles;

    public ParkingManager() {
        this.parkingSlots = new HashMap<>();
        this.waitingVehicles = new LinkedList<>();
    }

    public void addParkingSlot(int slotNumber, String slotType) {
        parkingSlots.put(slotNumber, new ParkingSlot(slotNumber, slotType));
        System.out.println("Parking slot " + slotNumber + " [" + slotType + "] added");
    }

    public void addToWaitingQueue(Vehicle vehicle) {
        waitingVehicles.offer(vehicle);
        System.out.println("Vehicle " + vehicle.vehicleNumber + " added to waiting queue");
    }

    public void allocateSlot(Vehicle vehicle) throws NoParkingSlotAvailableException {
        int nearestSlot = findNearestFreeSlot(vehicle.getVehicleType());
        if (nearestSlot == -1) {
            throw new NoParkingSlotAvailableException(
                "No parking slots available for " + vehicle.getVehicleType()
            );
        }

        ParkingSlot slot = parkingSlots.get(nearestSlot);
        slot.allocateVehicle(vehicle);
        System.out.println("Vehicle " + vehicle.vehicleNumber + " allocated to Slot " + nearestSlot);
    }

    public void allocateNextFromQueue() throws NoParkingSlotAvailableException {
        if (waitingVehicles.isEmpty()) {
            System.out.println("No vehicles in waiting queue");
            return;
        }

        Vehicle vehicle = waitingVehicles.poll();
        allocateSlot(vehicle);
    }

    private int findNearestFreeSlot(String vehicleType) {
        List<Integer> slotNumbers = new ArrayList<>(parkingSlots.keySet());
        slotNumbers.sort((a, b) -> a - b);

        for (int slotNumber : slotNumbers) {
            ParkingSlot slot = parkingSlots.get(slotNumber);
            if (!slot.isOccupied && slot.slotType.equalsIgnoreCase(vehicleType)) {
                return slotNumber;
            }
        }
        return -1;
    }

    public void releaseSlot(int slotNumber) {
        if (!parkingSlots.containsKey(slotNumber)) {
            System.out.println("Invalid slot number: " + slotNumber);
            return;
        }

        ParkingSlot slot = parkingSlots.get(slotNumber);
        if (!slot.isOccupied) {
            System.out.println("Slot " + slotNumber + " is already empty");
            return;
        }

        String vehicleNumber = slot.currentVehicle.vehicleNumber;
        slot.releaseSlot();
        System.out.println("Slot " + slotNumber + " released (Vehicle " + vehicleNumber + " departed)");
    }

    public void displaySlotStatus() {
        System.out.println("\n--- Parking Slot Status ---");
        List<Integer> slotNumbers = new ArrayList<>(parkingSlots.keySet());
        slotNumbers.sort((a, b) -> a - b);

        for (int slotNumber : slotNumbers) {
            System.out.println(parkingSlots.get(slotNumber));
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
        System.out.println("\n--- Parking Statistics ---");
        System.out.println("Total Slots: " + parkingSlots.size());

        int occupied = 0;
        int carSlots = 0;
        int bikeSlots = 0;

        for (ParkingSlot slot : parkingSlots.values()) {
            if (slot.isOccupied) occupied++;
            if (slot.slotType.equalsIgnoreCase("Car")) carSlots++;
            if (slot.slotType.equalsIgnoreCase("Bike")) bikeSlots++;
        }

        System.out.println("Occupied Slots: " + occupied);
        System.out.println("Available Slots: " + (parkingSlots.size() - occupied));
        System.out.println("Car Slots: " + carSlots);
        System.out.println("Bike Slots: " + bikeSlots);
        System.out.println("Vehicles Waiting: " + waitingVehicles.size());
    }
}
