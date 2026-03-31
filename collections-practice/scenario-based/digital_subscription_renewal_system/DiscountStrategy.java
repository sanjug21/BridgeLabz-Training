// Strategy Pattern for Discount Calculation
interface DiscountStrategy {
    double applyDiscount(double basePrice, int loyaltyMonths);
    String getDescription();
}

class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double basePrice, int loyaltyMonths) {
        return basePrice;
    }

    @Override
    public String getDescription() {
        return "No Discount";
    }
}

class StudentDiscountStrategy implements DiscountStrategy {
    private static final double DISCOUNT_PERCENTAGE = 0.30; // 30% off

    @Override
    public double applyDiscount(double basePrice, int loyaltyMonths) {
        return basePrice * (1 - DISCOUNT_PERCENTAGE);
    }

    @Override
    public String getDescription() {
        return "Student Discount (30% OFF)";
    }
}

class SeniorCitizenDiscountStrategy implements DiscountStrategy {
    private static final double DISCOUNT_PERCENTAGE = 0.40; // 40% off

    @Override
    public double applyDiscount(double basePrice, int loyaltyMonths) {
        return basePrice * (1 - DISCOUNT_PERCENTAGE);
    }

    @Override
    public String getDescription() {
        return "Senior Citizen Discount (40% OFF)";
    }
}

class LoyaltyDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double basePrice, int loyaltyMonths) {
        // 5% discount for every 6 months, max 25%
        double discountPercentage = Math.min((loyaltyMonths / 6) * 0.05, 0.25);
        return basePrice * (1 - discountPercentage);
    }

    @Override
    public String getDescription() {
        return "Loyalty Discount (5% per 6 months, max 25%)";
    }
}

class SeasonalDiscountStrategy implements DiscountStrategy {
    private static final double DISCOUNT_PERCENTAGE = 0.20; // 20% off

    @Override
    public double applyDiscount(double basePrice, int loyaltyMonths) {
        return basePrice * (1 - DISCOUNT_PERCENTAGE);
    }

    @Override
    public String getDescription() {
        return "Seasonal Discount (20% OFF)";
    }
}
