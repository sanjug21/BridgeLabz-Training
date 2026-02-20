
public class WaterTank {
    private String tankId;
    private double capacity;
    private double currentLevel;

    public WaterTank(String tankId, double capacity, double currentLevel) throws InvalidWaterLevelException {
        this.tankId = tankId;
        this.capacity = capacity;
        setCurrentLevel(currentLevel);
    }

    public String getTankId() {
        return tankId;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(double currentLevel) throws InvalidWaterLevelException {
        if (currentLevel > capacity) {
            throw new InvalidWaterLevelException("Water level " + currentLevel + " liters exceeds tank capacity of " + capacity + " liters for Tank " + tankId);
        }
        if (currentLevel < 0) {
            throw new InvalidWaterLevelException("Water level cannot be negative for Tank " + tankId);
        }
        this.currentLevel = currentLevel;
    }

    // Calculate usage percentage
    public double getUsagePercentage() {
        return (currentLevel / capacity) * 100;
    }

    // Check if alert needs to be triggered
    public boolean needsAlert() {
        return getUsagePercentage() < 20;
    }

    @Override
    public String toString() {
        return String.format("Tank ID: %s | Capacity: %.2f L | Current Level: %.2f L | Usage: %.2f%%",
                tankId, capacity, currentLevel, getUsagePercentage());
    }
}
