class Platform {
    int platformNumber;
    String platformType;
    boolean isOccupied;
    String currentTrain;

    public Platform(int platformNumber, String platformType) {
        this.platformNumber = platformNumber;
        this.platformType = platformType;
        this.isOccupied = false;
        this.currentTrain = null;
    }

    public void allocateTrain(String trainNumber) {
        this.isOccupied = true;
        this.currentTrain = trainNumber;
    }

    public void releasePlatform() {
        this.isOccupied = false;
        this.currentTrain = null;
    }

    @Override
    public String toString() {
        String status = isOccupied ? "Occupied (Train: " + currentTrain + ")" : "Available";
        return "Platform " + platformNumber + " [" + platformType + "] - " + status;
    }
}
