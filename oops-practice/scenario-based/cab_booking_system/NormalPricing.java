
package cab_booking_system;

class NormalPricing implements FareCalculator {
    private final double normalPrice;

    NormalPricing(double normalPrice) {
        this.normalPrice = normalPrice;
    }

    @Override
    public double calculateFare(double distance) {
        return distance*normalPrice;
    }

}

