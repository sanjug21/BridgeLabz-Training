package unit_conversion_tool;

public class UnitConversionTool {

    public static void main(String[] args) {
        double distanceKm = 120.5;
        double weightKg = 75.0;

        double distanceMiles = UnitConverter.kmToMiles(distanceKm);
        double weightLbs = UnitConverter.kgToLbs(weightKg);

        System.out.println("Unit Conversion Tool");
        System.out.println("=====================");
        System.out.printf("%.2f km = %.2f miles%n", distanceKm, distanceMiles);
        System.out.printf("%.2f kg = %.2f lbs%n", weightKg, weightLbs);
    }
}
