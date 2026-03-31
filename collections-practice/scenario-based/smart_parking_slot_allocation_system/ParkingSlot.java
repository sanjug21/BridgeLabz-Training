class ParkingSlot {
    int slotNumber;
    String slotType;
    boolean isOccupied;
    Vehicle currentVehicle;

    public ParkingSlot(int slotNumber, String slotType) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        this.isOccupied = false;
        this.currentVehicle = null;
    }

    public void allocateVehicle(Vehicle vehicle) {
        this.isOccupied = true;
        this.currentVehicle = vehicle;
    }

    public void releaseSlot() {
        this.isOccupied = false;
        this.currentVehicle = null;
    }

    @Override
    public String toString() {
        if (isOccupied) {
            return "Slot " + slotNumber + " [" + slotType + "] | Occupied | " + currentVehicle.vehicleNumber;
        } else {
            return "Slot " + slotNumber + " [" + slotType + "] | Available";
        }
    }
}
