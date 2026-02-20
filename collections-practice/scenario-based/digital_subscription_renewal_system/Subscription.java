import java.time.LocalDate;

class Subscription {
    String planName;
    double basePrice;
    LocalDate startDate;
    LocalDate expiryDate;
    boolean autoRenew;
    String paymentMethod;

    public Subscription(String planName, double basePrice, LocalDate startDate, 
                       int durationMonths, boolean autoRenew, String paymentMethod) {
        this.planName = planName;
        this.basePrice = basePrice;
        this.startDate = startDate;
        this.expiryDate = startDate.plusMonths(durationMonths);
        this.autoRenew = autoRenew;
        this.paymentMethod = paymentMethod;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public boolean isExpiringSoon() {
        return LocalDate.now().plusDays(7).isAfter(expiryDate) && !isExpired();
    }

    public void renew(int months) {
        this.startDate = LocalDate.now();
        this.expiryDate = startDate.plusMonths(months);
    }

    @Override
    public String toString() {
        String status = isExpired() ? "EXPIRED" : (isExpiringSoon() ? "EXPIRING SOON" : "ACTIVE");
        return planName + " | ₹" + basePrice + "/month | Expires: " + expiryDate + 
               " | Auto-Renew: " + (autoRenew ? "ON" : "OFF") + " | Status: " + status;
    }
}
