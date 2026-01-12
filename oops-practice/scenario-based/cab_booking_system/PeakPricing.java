package cab_booking_system;

class PeakPricing implements FareCalculator {
    private final double peakPrice;

    PeakPricing(double peakPrice) {
        this.peakPrice = peakPrice;
    }

    @Override
    public double calculateFare(double distance) {
        return distance*peakPrice;
    }

}