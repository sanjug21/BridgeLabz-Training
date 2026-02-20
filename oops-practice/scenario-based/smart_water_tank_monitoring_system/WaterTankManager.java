
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WaterTankManager {
    private List<WaterTank> tanks;

    public WaterTankManager() {
        this.tanks = new ArrayList<>();
    }

    // Add a new water tank
    public void addTank(WaterTank tank) {
        tanks.add(tank);
        System.out.println("Tank " + tank.getTankId() + " added successfully.");
    }

    // Update water level in a tank
    public void updateWaterLevel(String tankId, double newLevel) {
        for (WaterTank tank : tanks) {
            if (tank.getTankId().equals(tankId)) {
                try {
                    tank.setCurrentLevel(newLevel);
                    System.out.println("Water level updated for Tank " + tankId);
                    
                    if (tank.needsAlert()) {
                        System.out.println("ALERT: Tank " + tankId + " has low water level (" + 
                                String.format("%.2f", tank.getUsagePercentage()) + "%)");
                    }
                } catch (InvalidWaterLevelException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Tank " + tankId + " not found.");
    }

    // Display all tanks
    public void displayAllTanks() {
        if (tanks.isEmpty()) {
            System.out.println("No tanks registered in the system.");
            return;
        }
        
        System.out.println("\n========== ALL WATER TANKS ==========");
        for (WaterTank tank : tanks) {
            System.out.println(tank);
            if (tank.needsAlert()) {
                System.out.println("  LOW WATER LEVEL ALERT");
            }
        }
        System.out.println("=====================================\n");
    }

    // Sort tanks by lowest level and display
    public void displayTanksByLowestLevel() {
        if (tanks.isEmpty()) {
            System.out.println("No tanks registered in the system.");
            return;
        }

        List<WaterTank> sortedTanks = new ArrayList<>(tanks);
        sortedTanks.sort(Comparator.comparingDouble(WaterTank::getCurrentLevel));

        System.out.println("\n========== TANKS SORTED BY LOWEST LEVEL ==========");
        for (WaterTank tank : sortedTanks) {
            System.out.println(tank);
            if (tank.needsAlert()) {
                System.out.println("  LOW WATER LEVEL ALERT");
            }
        }
        System.out.println("==================================================\n");
    }

    // Generate alerts for all low-level tanks
    public void generateAlerts() {
        List<WaterTank> alertTanks = new ArrayList<>();
        
        for (WaterTank tank : tanks) {
            if (tank.needsAlert()) {
                alertTanks.add(tank);
            }
        }

        if (alertTanks.isEmpty()) {
            System.out.println("\nNo tanks require alerts. All tanks have sufficient water levels.\n");
            return;
        }

        System.out.println("\n========== LOW WATER LEVEL ALERTS ==========");
        for (WaterTank tank : alertTanks) {
            System.out.println("ALERT: " + tank);
        }
        System.out.println("============================================\n");
    }

    // Get tank by ID
    public WaterTank getTankById(String tankId) {
        for (WaterTank tank : tanks) {
            if (tank.getTankId().equals(tankId)) {
                return tank;
            }
        }
        return null;
    }

    // Calculate total water in all tanks
    public double getTotalWaterLevel() {
        double total = 0;
        for (WaterTank tank : tanks) {
            total += tank.getCurrentLevel();
        }
        return total;
    }

    // Calculate total capacity
    public double getTotalCapacity() {
        double total = 0;
        for (WaterTank tank : tanks) {
            total += tank.getCapacity();
        }
        return total;
    }

    // Display system summary
    public void displaySystemSummary() {
        if (tanks.isEmpty()) {
            System.out.println("No tanks registered in the system.");
            return;
        }

        double totalCapacity = getTotalCapacity();
        double totalWater = getTotalWaterLevel();
        double overallUsage = (totalWater / totalCapacity) * 100;

        System.out.println("\n========== SYSTEM SUMMARY ==========");
        System.out.println("Total Tanks: " + tanks.size());
        System.out.println("Total Capacity: " + String.format("%.2f", totalCapacity) + " L");
        System.out.println("Total Water: " + String.format("%.2f", totalWater) + " L");
        System.out.println("Overall Usage: " + String.format("%.2f", overallUsage) + "%");
        
        long alertCount = tanks.stream().filter(WaterTank::needsAlert).count();
        System.out.println("Tanks Needing Alert: " + alertCount);
        System.out.println("====================================\n");
    }
}
