class ChargingSlot {
    int slotId;
    boolean isOccupied;
    Vehicle currentVehicle;
    double unitsConsumed;

    public ChargingSlot(int slotId) {
        this.slotId = slotId;
        this.isOccupied = false;
        this.currentVehicle = null;
        this.unitsConsumed = 0;
    }

    public void allocateVehicle(Vehicle vehicle) {
        this.isOccupied = true;
        this.currentVehicle = vehicle;
        this.unitsConsumed = 0;
    }

    public void releaseSlot() {
        this.isOccupied = false;
        this.currentVehicle = null;
        this.unitsConsumed = 0;
    }

    @Override
    public String toString() {
        if (isOccupied) {
            return "Slot " + slotId + " | Occupied | Vehicle: " + 
                   currentVehicle.vehicleNumber + " | Units: " + unitsConsumed;
        } else {
            return "Slot " + slotId + " | Available";
        }
    }
}
