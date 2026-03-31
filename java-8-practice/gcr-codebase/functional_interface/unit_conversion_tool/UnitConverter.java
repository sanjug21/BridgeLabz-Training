package unit_conversion_tool;

public interface UnitConverter {

    static double kmToMiles(double kilometers) {
        return kilometers * 0.621371;
    }

    static double kgToLbs(double kilograms) {
        return kilograms * 2.20462;
    }
}
