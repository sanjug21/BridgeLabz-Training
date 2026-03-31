package hotel_management_system;

// Interface: Strategy for calculating room price
public interface PricingStrategy {
    double calculatePrice(double basePrice, int nights);
}