package hotel_management_system;

// Polymorphism: Concrete strategy for Seasonal pricing
public class SeasonalPricingStrategy implements PricingStrategy {
    private boolean isPeakSeason;

    public SeasonalPricingStrategy(boolean isPeakSeason) {
        this.isPeakSeason = isPeakSeason;
    }

    @Override
    public double calculatePrice(double basePrice, int nights) {
        // Apply 50% surge charge during peak season
        return isPeakSeason ? (basePrice * nights * 1.5) : (basePrice * nights);
    }
}