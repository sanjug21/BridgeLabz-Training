class EVVehicle {
    String vehicleNumber;
    String ownerName;
    String vehicleType;

    public EVVehicle(String vehicleNumber, String ownerName, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return vehicleNumber + " | " + ownerName + " | " + vehicleType;
    }
}
