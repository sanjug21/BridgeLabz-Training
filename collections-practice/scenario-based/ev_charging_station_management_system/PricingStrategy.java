interface PricingStrategy {
    double calculateBill(double units);
    String getDescription();
}

class NormalPricing implements PricingStrategy {
    private static final double RATE_PER_UNIT = 8.0;

    @Override
    public double calculateBill(double units) {
        return units * RATE_PER_UNIT;
    }

    @Override
    public String getDescription() {
        return "Normal Hours (Rs. 8/unit)";
    }
}

class PeakHourPricing implements PricingStrategy {
    private static final double RATE_PER_UNIT = 12.0;

    @Override
    public double calculateBill(double units) {
        return units * RATE_PER_UNIT;
    }

    @Override
    public String getDescription() {
        return "Peak Hours (Rs. 12/unit)";
    }
}

class OffPeakPricing implements PricingStrategy {
    private static final double RATE_PER_UNIT = 5.0;

    @Override
    public double calculateBill(double units) {
        return units * RATE_PER_UNIT;
    }

    @Override
    public String getDescription() {
        return "Off-Peak Hours (Rs. 5/unit)";
    }
}
